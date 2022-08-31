package ci.dgmp.personnel.model.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
//@DiscriminatorValue("AUTHORISATION")
@AllArgsConstructor
@NoArgsConstructor @Getter @Setter @Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "demType")
public class DemandeAbsence extends Demande
{
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Spring formate la date pour nous
    protected LocalDate demDateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Spring formate la date pour nous
    protected LocalDate demDateFin;
    protected long demNbreJour;
    protected String DemDestination;
}
