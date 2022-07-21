package ci.dgmp.personnel.security.model.dto.request;

import ci.dgmp.personnel.security.model.dto.validators.ConfirmPassword;
import ci.dgmp.personnel.security.model.dto.validators.EmailAndTokenExist;
import ci.dgmp.personnel.security.model.dto.validators.NoneExpiredToken;
import ci.dgmp.personnel.security.model.dto.validators.UniqueLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor @Builder @ConfirmPassword @EmailAndTokenExist
public class ResetUserAccountDto
{
    @Email(message = "Adresse mail invalide")
    @NotBlank(message = "L'adresse email ne peut être vide")
    @NotNull(message = "L'adresse email ne peut être null")
    private String userEmail;

    @NotBlank(message = "Le mot de passe ne peut être vide")
    @Length(message = "Le mot de passe doit contenir au moins 3 caractères", min = 3)
    @NotNull(message = "Le mot de passe ne peut être null")
    private String userPassword;

    @NotBlank(message = "Le mot de passe ne peut être vide")
    @NotNull(message = "Le mot de passe ne peut être null")
    private String userConfirmPassword;

    @NotBlank(message = "Le token ne peut être vide")
    @NotNull(message = "Le token ne peut être null")
    @NoneExpiredToken
    private String token;
}
