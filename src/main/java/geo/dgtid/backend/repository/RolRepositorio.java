package geo.dgtid.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Long>{
    Optional<Rol> findByName(String name);
}
