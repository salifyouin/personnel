package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistingEmailValidator.class})
@Documented
public @interface ExistingEmail {
    String message() default "Adresse mail inconnue";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component
@RequiredArgsConstructor
class ExistingEmailValidator implements ConstraintValidator<ExistingEmail,String> {
    private final UserRepository userRepo;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepo.existsByUserEmail(s);
    }
}
