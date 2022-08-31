package ci.dgmp.personnel.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@DiscriminatorValue("AUTHORISATION")
@AllArgsConstructor
@NoArgsConstructor @Getter @Setter @Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "demType")
public class DemandeMouvement extends Demande
{
    @ManyToOne
    @JoinColumn(name = "demStrActuelleId")
    protected Structure structureActuelle;
    @ManyToOne
    @JoinColumn(name = "demStrAccueilId")
    protected Structure structureAccueil;
}
