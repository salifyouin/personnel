package ci.dgmp.personnel.model.dto;

import ci.dgmp.personnel.service.validator.agent.ExisteMatricule;
import ci.dgmp.personnel.service.validator.agent.ExisteUsername;
import ci.dgmp.personnel.service.validator.agent.MailExist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AgentReqDto {

    @NotBlank(message = "Le numero matricule ne doit pas etre vide")
    @NotNull(message = "Le numero matricule ne doit pas etre vide")
    @Size(min = 3,message = "le matricule doit compôrter au moin 3 caractère")
    @ExisteMatricule
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
    @NotNull(message = "Selectionnez la date de naissance")
    //@Past(message = "Date invalide")
    private LocalDate agtDateNaissance;

    private String agtTel;

    @Email(message = "Veuillez saisir une adresse email valide")
    @MailExist
    private String agtAdresse;

    private String agtSituationMat;

    private Boolean agtActif;

    @NotNull(message = "Selectionez la fonction")
    private Long agtFonId;

    @NotNull(message = "Selectionez la structure")
    private Long agtStrId;

    private Long agtTypId;

    @NotNull(message = "Selectionez le grade de l'agent")
    private Long agtGradeId;

}
