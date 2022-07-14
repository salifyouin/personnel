package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {OldPassword.OldPasswordValidator.class})
@Documented
public @interface OldPassword
{
    String message() default "Ancien mot de passe incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Component
    @RequiredArgsConstructor
    class OldPasswordValidator implements ConstraintValidator<OldPassword, String>
    {
        private final ISecurityContextService scs;
        private final PasswordEncoder passwordEncoder;
        private final UserRepository userRepo;
        @Override
        public boolean isValid(String oldPassword, ConstraintValidatorContext context)
        {
            String loadedPassword = userRepo.getUserPassword(scs.getAuthUsername());
            return passwordEncoder.matches(oldPassword, loadedPassword);
        }
    }
}
