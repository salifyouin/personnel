package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("affectation")
public abstract class Affectation extends DemandeMouvement {
  private String libelleAffectation;
}
