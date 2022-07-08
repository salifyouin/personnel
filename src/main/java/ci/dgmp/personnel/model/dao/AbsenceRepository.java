package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
}
