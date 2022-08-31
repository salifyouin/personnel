package ci.dgmp.personnel.model.entities;

import ci.dgmp.personnel.model.enums.StatutTraitement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long traitId;
    private LocalDateTime traitDate;
    private String traitObservation;
//    @ManyToOne
//    @JoinColumn(name = "traiDemId")
//    private Demande demande;
    @ManyToOne
    @JoinColumn(name = "traiTypId")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "traiAgtId")
    private Agent agent;
//    @Enumerated(EnumType.STRING)
//    private StatutTraitement traiStatut;
}
