package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.entities.Absence;
import ci.dgmp.personnel.model.entities.DemandeAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    //Afficher la liste des demande d'absence
    @Query("select d from Absence d where d.agent.agtId = ?1 order by d.demId DESC")
    List<Absence> findDemandeAbsence(Long agtId);
}
