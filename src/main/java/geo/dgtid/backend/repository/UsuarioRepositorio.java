package geo.dgtid.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    //Optional<Usuario> findByEmail(String email);
    //Optional<Usuario> findByUsernameOrEmail(String username, String email);
    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);
    //Boolean existsByEmail(String email);
}
