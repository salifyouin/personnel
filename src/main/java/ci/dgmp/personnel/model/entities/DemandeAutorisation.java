package ci.dgmp.personnel.model.entities;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity @DiscriminatorValue("AUTH")@AllArgsConstructor
@NoArgsConstructor @Getter @Setter @Builder
public class DemandeAutorisation extends Demande
{
    private LocalDate demDateDebut;
    private long demNbrJour;
    private LocalDate demDateFin;
}
