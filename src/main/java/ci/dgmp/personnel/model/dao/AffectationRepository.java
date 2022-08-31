package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffectationRepository extends JpaRepository<Affectation, Long> {
}
