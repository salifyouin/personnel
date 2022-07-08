package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "demType",length = 4)
public abstract class Demande {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long demId;
  protected String demObjet;
  protected LocalDateTime demDateSaisie;
  @ManyToOne
  @JoinColumn(name = "demAgtId")
  protected Agent agent;
//  @ManyToOne
//  @JoinColumn(name = "demTypId")
//  private Type type;
}
