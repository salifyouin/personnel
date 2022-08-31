package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.AbsenceRepository;
import ci.dgmp.personnel.model.entities.Absence;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.DemandeAbsence;
import ci.dgmp.personnel.security.service.implementation.SecurityContextService;
import ci.dgmp.personnel.service.interfac.AbsenceIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoPeriod;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceService implements AbsenceIservice {
    private final AbsenceRepository absRepo;
    private final SecurityContextService scs;
    @Override
    public void saveAbsence(Absence absence) {
        absence.setDemDateSaisie(LocalDateTime.now());
        absence.setDemNbreJour(Period.between(absence.getDemDateDebut(), absence.getDemDateFin()).getDays());
        absence.setAgent(scs.getAuthUser().getAgent());
        absRepo.save(absence);
    }

    @Override
    public List<Absence> findDemandeAbsence(Long agtId) {
        // return absRepo.findAll();
        return absRepo.findDemandeAbsence(agtId);
    }
}
