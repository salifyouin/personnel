package ci.dgmp.personnel.model.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DemandeAbsenceInfo {
    Long getDemId();

    String getDemDestination();

    LocalDate getDemDateDebut();

    LocalDate getDemDateFin();

    LocalDateTime getDemDateSaisie();

    long getDemNbreJour();

    String getDemObjet();

    AgentInfo getAgent();

    interface AgentInfo {
        Long getAgtId();
    }
}
