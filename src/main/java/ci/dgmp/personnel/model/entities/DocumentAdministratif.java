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
public class DocumentAdministratif {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docId;
    private String docNom;
    private String docChemain;
    @ManyToOne
    @JoinColumn(name = "docDemId")
    private Demande demande;
    @ManyToOne
    @JoinColumn(name = "docTypId")
    private Type type;
}
