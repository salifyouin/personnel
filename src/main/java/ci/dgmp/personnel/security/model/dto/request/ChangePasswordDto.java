package ci.dgmp.personnel.security.model.dto.request;

import ci.dgmp.personnel.security.model.dto.validators.ConfirmPassword;
import ci.dgmp.personnel.security.model.dto.validators.OldPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@ConfirmPassword
public class ChangePasswordDto
{
    @OldPassword
    private String oldPassword;
    @NotBlank(message = "Le mot de passe ne peut être vide")
    @Length(message = "Le mot de passe doit contenir au moins 3 caractères", min = 3)
    @NotNull(message = "Le mot de passe ne peut être null")
    private String userPassword;
    @NotBlank(message = "Le mot de passe ne peut être vide")
    @NotNull(message = "Le mot de passe ne peut être null")
    private String userConfirmPassword;
}
