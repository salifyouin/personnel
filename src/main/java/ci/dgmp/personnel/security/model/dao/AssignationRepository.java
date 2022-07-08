package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.entities.Assignation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignationRepository extends JpaRepository<Assignation, Long> {
}
