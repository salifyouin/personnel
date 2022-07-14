package ci.dgmp.personnel.security.model.dto.request;

import ci.dgmp.personnel.security.model.dto.validators.ConfirmPassword;
import ci.dgmp.personnel.security.model.dto.validators.UniqueEmail;
import ci.dgmp.personnel.security.model.dto.validators.UniqueLogin;
import ci.dgmp.personnel.security.model.dto.validators.UniqueTel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ConfirmPassword
public class UserReqDto {
    @NotBlank(message = "Le nom ne peut être vide")
    @Length(message = "Le nom doit contenir au moins 2 caractères", min = 2)
    @NotNull(message = "Le nom ne peut être null")
    private String userNom;

    @NotBlank(message = "Le prénom ne peut être vide")
    @Length(message = "Le prénom doit contenir au moins 2 caractères", min = 2)
    @NotNull(message = "Le prénom ne peut être null")
    private String userPrenom;

    @NotBlank(message = "Le login ne peut être vide")
    @Length(message = "Le login doit contenir au moins 3 caractères", min = 3)
    @NotNull(message = "Le login ne peut être null")
    @UniqueLogin
    private String userLogin;

    @NotBlank(message = "Le mot de passe ne peut être vide")
    @Length(message = "Le mot de passe doit contenir au moins 3 caractères", min = 3)
    @NotNull(message = "Le mot de passe ne peut être null")
    private String userPassword;

    private String userConfirmPassword;

    @NotBlank(message = "Le numéro de téléphone ne peut être vide")
    @Length(message = "Le numéro de téléphone doit contenir 10 caractères", min = 10, max = 10)
    @NotNull(message = "Le numéro de téléphone ne peut être null")
    @UniqueTel
    private String userTelephone;

    @Email(message = "Adresse mail invalide")
    @NotBlank(message = "Le numéro de téléphone ne peut être vide")
    @NotNull(message = "Le numéro de téléphone ne peut être null")
    @UniqueEmail
    private String userEmail;

    private boolean userActive;

    private Long agtId;
}
