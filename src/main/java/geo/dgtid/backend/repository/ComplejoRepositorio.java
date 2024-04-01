package geo.dgtid.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Complejo;
import java.util.Optional;

public interface ComplejoRepositorio extends JpaRepository<Complejo, Long>{
    Optional<Complejo> findByNombreComplejo(String nombreComplejo);
}
