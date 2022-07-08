package ci.dgmp.personnel.security.model.entities;

import ci.dgmp.personnel.model.entities.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "assType")
public abstract class Assignation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long assId;
    protected LocalDate assDateDebut;
    protected LocalDate assDateFin;
    protected boolean assActive;
    @ManyToOne @JoinColumn(name = "assStrId")
    protected Structure structure;
}
