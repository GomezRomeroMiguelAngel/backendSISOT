package geo.dgtid.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Nivel;
import java.util.Optional;

public interface NivelRepositorio extends JpaRepository<Nivel, Long>{
    Optional<Nivel> findByDescripcionNivel(String descripcionNivel);
}
