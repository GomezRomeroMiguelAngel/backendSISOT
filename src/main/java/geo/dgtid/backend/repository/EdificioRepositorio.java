package geo.dgtid.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Edificio;
import java.util.Optional;

public interface EdificioRepositorio extends JpaRepository<Edificio, Long>{
    Optional<Edificio> findByNombreEdificio(String nombreEdificio);
}
