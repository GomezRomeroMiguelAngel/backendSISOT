package geo.dgtid.backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import geo.dgtid.backend.models.Usuario;
import geo.dgtid.backend.repository.UsuarioRepositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/usuarios")
    public List<Usuario> allUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    @PutMapping("/usuario/{id}")
    public Usuario cambiarContrasena(@PathVariable("id") Long id, @RequestBody Usuario user) {
        return usuarioRepositorio.save(user);
    }

    @DeleteMapping("/usuario/{id}")
	public void deleteUusuario(@PathVariable("id") Long id) {
		usuarioRepositorio.deleteById(id);
	}
}
