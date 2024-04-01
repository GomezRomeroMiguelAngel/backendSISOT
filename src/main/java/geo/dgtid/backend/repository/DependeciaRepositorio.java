package geo.dgtid.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import geo.dgtid.backend.models.Dependencia;
import java.util.Optional;

public interface DependeciaRepositorio extends JpaRepository<Dependencia, Long>{
    Optional<Dependencia> findByNombreDependencia(String nombreDependencia);
}
