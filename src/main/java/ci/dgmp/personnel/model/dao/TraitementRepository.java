package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.entities.Traitement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraitementRepository extends JpaRepository<Traitement, Long> {
}
