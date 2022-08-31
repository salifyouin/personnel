package ci.dgmp.personnel.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Type {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long typId;
 private String typCode;
 private String typLibelle;
 @ManyToOne
 @JoinColumn(name = "typTypId")
 private Type type;
 private Long typLevel;

 public Type(Long typId) {
  this.typId = typId;
 }

}
