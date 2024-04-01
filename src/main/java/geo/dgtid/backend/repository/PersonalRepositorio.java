package geo.dgtid.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Personal;


public interface PersonalRepositorio extends JpaRepository<Personal, Long>{
    Optional<Personal> findByNombre(String nombre);
    Optional<Personal> findByRfc(String rfc);
    Optional<Personal> findByCurp(String curp);
    Optional<Personal> findByEmail(String email);
    boolean existsByRfc(String rfc);
    boolean existsByCurp(String curp);
    boolean existsByEmail(String email);
}
