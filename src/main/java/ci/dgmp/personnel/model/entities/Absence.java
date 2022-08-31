package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("Absence")
public class Absence extends DemandeAbsence{
    private String demMotif;
}
