package ci.dgmp.personnel.model.dao;


import ci.dgmp.personnel.model.entities.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {
}
