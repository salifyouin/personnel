package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("attPresence")
public class AttestionPresence extends DemandeActe {
    private String libelleAttesTravail;

}
