package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.entities.Absence;
import ci.dgmp.personnel.model.entities.DemandeAbsence;

import java.util.List;

public interface AbsenceIservice {
    void saveAbsence(Absence absence);
    List<Absence> findDemandeAbsence(Long agtId);
}
