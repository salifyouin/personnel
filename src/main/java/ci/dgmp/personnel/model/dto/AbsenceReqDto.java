package ci.dgmp.personnel.model.dto;

import ci.dgmp.personnel.model.entities.Agent;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AbsenceReqDto {
    protected String demObjet;
    protected LocalDateTime demDateSaisie;
    protected long demAgtId;
    protected LocalDate demDateDebut;
    protected LocalDate demDateFin;
    protected long demNbreJour;
    protected String DemDestination;
    private String libelleAbsence;
}

