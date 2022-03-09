package one.digitalinnovation.gof.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import one.digitalinnovation.gof.model.Profissional;

@Repository
public interface ProfissionalRepository extends CrudRepository<Profissional, Long>{

}
