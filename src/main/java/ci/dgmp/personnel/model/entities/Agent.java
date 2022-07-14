package ci.dgmp.personnel.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agtId;
    private String agtMatricule;
    private String agtNom;
    private String agtPrenom;
    private LocalDate agtDateNaissance;
    private String agtTel;
    private String agtAdresse;
    private String agtSituationMat;
    private String agtUserName;
    private String agtPassword;
    private Boolean agtActif;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "agtStrId")
    private Structure structure;
    @ManyToOne
    @JoinColumn(name = "agtTypId")
    private Type typeAgt;
    @ManyToOne
    @JoinColumn(name = "agtFonId")
    private Type fonction;
    @ManyToOne
    @JoinColumn(name = "agtGrade")
    private Type grade;

    public Agent(long demAgtId) {
    }
}
