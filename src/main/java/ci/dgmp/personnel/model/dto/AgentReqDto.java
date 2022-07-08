package ci.dgmp.personnel.model.dto;

import ci.dgmp.personnel.model.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AgentReqDto {

    @NotBlank(message = "Le numero matricule ne doit pas etre vide")
    @NotNull
    @Size(min = 3,message = "le matricule doit compôrter au moin 3 caractère")
    private String agtMatricule;

    @NotBlank(message = "Nom Obligatoire")
    @NotNull(message = "Nom Obligatoire")
    @Size(min = 3,message = "nom en dessous de 3 caractere")
    private String agtNom;

    @NotBlank(message = "Prenom Obligatoire")
    @NotNull(message = "Prenom Obligatoire")
    @Size(min = 3,message = "nom en dessous de 3 caractere")
    private String agtPrenom;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // Spring formate la date pour nous
    private LocalDate agtDateNaissance;

    @Column(unique = true)
    private String agtTel;

    @Email(message = "Veuillez saisir une adresse email valide")
    private String agtAdresse;

    private String agtSituationMat;

    @Column(unique = true)
    @NotBlank(message = "Le nom d'utilisateur ne peut pas etre vide")
    @NotNull(message = "Le nom d'utilisateur ne peut pas etre vide")
    private String agtUserName;

    private String agtPassword;

    private Boolean agtActif;

    private Long agtFonId;

    private Long agtStrId;

    private Long agtTypId;

    private Long agtGradeId;

}
