package geo.dgtid.backend.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import geo.dgtid.backend.Services.EnvioEmailService;
import geo.dgtid.backend.Services.PersonalService;
import geo.dgtid.backend.models.Complejo;
import geo.dgtid.backend.models.Dependencia;
import geo.dgtid.backend.models.Edificio;
import geo.dgtid.backend.models.Nivel;
import geo.dgtid.backend.models.Personal;
import geo.dgtid.backend.models.Rol;
import geo.dgtid.backend.models.Usuario;
import geo.dgtid.backend.payload.LoginDto;
import geo.dgtid.backend.payload.PersonalDto;
import geo.dgtid.backend.repository.ComplejoRepositorio;
import geo.dgtid.backend.repository.DependeciaRepositorio;
import geo.dgtid.backend.repository.EdificioRepositorio;
import geo.dgtid.backend.repository.NivelRepositorio;
import geo.dgtid.backend.repository.PersonalRepositorio;
import geo.dgtid.backend.repository.RolRepositorio;
import geo.dgtid.backend.repository.UsuarioRepositorio;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepositorio userRepository;

    @Autowired
    private PersonalRepositorio personalRepositorio;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private RolRepositorio roleRepository;

    @Autowired
    private DependeciaRepositorio dependeciaRepositorio;

    @Autowired
    private EdificioRepositorio edificioRepositorio;

    @Autowired
    private ComplejoRepositorio complejoRepositorio;

    @Autowired
    private NivelRepositorio nivelRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Autowired
    // private EnvioEmailService emailService;


    @PostMapping("/signup")
    public ResponseEntity<?> registrarPersonalYUsuario(@RequestBody PersonalDto personalDto) {

        // Revisa si ya existe un RFC asosciado a un personal
        if(personalRepositorio.existsByRfc(personalDto.getRfc())){
            return new ResponseEntity<>("¡El RFC ya está en uso! ", HttpStatus.BAD_REQUEST);
        }

        // Revisa si ya existe una CURP asosiada a un personal
        if(personalRepositorio.existsByCurp(personalDto.getCurp())){
            return new ResponseEntity<>("¡La CURP ya está en uso! ", HttpStatus.BAD_REQUEST);
        }

        Personal personal = new Personal();
        personal.setNombre(personalDto.getNombre());
        personal.setApellidoPaterno(personalDto.getApellidoPaterno());
        personal.setApellidoMaterno(personalDto.getApellidoMaterno());
        personal.setRfc(personalDto.getRfc());
        personal.setCurp(personalDto.getCurp());

        // Obtener el correo electrónico del SignUpDto
        String email = personalDto.getEmail();
        if (email == null) {
            return new ResponseEntity<>("El correo electrónico es obligatorio", HttpStatus.BAD_REQUEST);
        }
        personal.setEmail(email);
        
        // Prueba para obtener el complejo correspondiente, por ejemplo Ciudad Judicial
        Complejo complejo = complejoRepositorio.findByNombreComplejo("Ciudad Judicial").orElse(null);
        if (complejo == null) {
            return new ResponseEntity<>("Complejo no encontrado", HttpStatus.NOT_FOUND);
        }

        // Prueba para obtener el nombre del Edificio correspondiente
        Edificio edificio = edificioRepositorio.findByNombreEdificio("Edificio 2").orElse(null);
        if (edificio == null) {
            return new ResponseEntity<>("Edificio no encontrado", HttpStatus.NOT_FOUND);
        }

        edificio.setComplejo(complejo);

        // Prueba para obtener el Nivel correspondiente
        Nivel nivel = nivelRepositorio.findByDescripcionNivel("Nivel 2").orElse(null);
        if (nivel == null) {
            return new ResponseEntity<>("Nivel no encontrado", HttpStatus.NOT_FOUND);
        }

        // Prueba para obtener el nombre de la dependencia correspondiente
        Dependencia dependencia = dependeciaRepositorio.findByNombreDependencia("DGTID").orElse(null);
        if (dependencia == null) {
            return new ResponseEntity<>("Dependencia no encontrada", HttpStatus.NOT_FOUND);
        }

        dependencia.setEdificio(edificio);
        
        dependencia.setNivel(nivel);

        personal.setDependencia(dependencia);

        personalService.save(personal);

        // Generar nombre de usuario y contraseña
        String username = personalDto.generarUserName();
        String password = PersonalDto.generarPassword();

        // Crear un nuevo usuario
        Usuario user = new Usuario();
        user.setUsername(username);

        user.setPassword(passwordEncoder.encode(password)); 

        // Prueba para obtener el rol correspondiente, por ejemplo, "ROLE_ENLACE"
        Rol roles = roleRepository.findByName("ROL_ENLACE").orElse(null);
        if (roles == null) {
            return new ResponseEntity<>("Rol no encontrado", HttpStatus.NOT_FOUND);
        }
        user.setRoles(Collections.singleton(roles));

        user.setPersonal(personal);

        // Guardar el usuario en la base de datos
        userRepository.save(user);

        //Envio del nombre de usuario y su contraseña al correo proporcionado
        //emailService.sendEmail(email, "Usuario y Contraseña - SISOT", "Su usuario es: "+ username + "\n Su password es: "+ password);
        
        // Retornar el usuario creado
        return new ResponseEntity<>("Usuario registrado exitosamente! " +
                "Username: " + username + ", Password: " + password, HttpStatus.OK);
    }
    
    @GetMapping("/roles")
    public List<Rol> allRoles() {
        return roleRepository.findAll();
    } 

    //Iniciar sesion con el nombre de usuario y su contraseña
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("¡Usuario inició sesión exitosamente!.", HttpStatus.OK);
    }

}
