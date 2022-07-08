package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class ParametreType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parTypId;
    private String parTypLibelle;
    private Boolean parTypActif;
    @ManyToOne
    @JoinColumn(name = "parTypTypId")
    private Type type;
}
