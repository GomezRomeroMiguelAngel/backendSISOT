package geo.dgtid.backend.Services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import geo.dgtid.backend.models.Usuario;
import geo.dgtid.backend.repository.UsuarioRepositorio;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private UsuarioRepositorio userRepository;

    public CustomUserDetailsService(UsuarioRepositorio userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Usuario user = userRepository.findByUsername(username)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("Usuario no encontrado por username: "+ username));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
