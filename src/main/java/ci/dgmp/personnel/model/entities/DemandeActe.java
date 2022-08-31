package ci.dgmp.personnel.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
//@DiscriminatorValue("AUTHORISATION")
@AllArgsConstructor
@NoArgsConstructor @Getter @Setter @Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "demType")
public class DemandeActe extends Demande
{
  protected String demTitreActe;
}
