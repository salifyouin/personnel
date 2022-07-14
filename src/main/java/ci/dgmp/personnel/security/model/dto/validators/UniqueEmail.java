package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueEmailValidator.class})
@Documented
public @interface UniqueEmail {
    String message() default "L'adresse mail existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component @RequiredArgsConstructor
class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
 private final UserRepository userRepo;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepo.existsByUserEmail(s);
    }
}
