package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueLoginValidator.class})
@Documented
public @interface UniqueLogin {
    String message() default "Le login existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component
@RequiredArgsConstructor
class UniqueLoginValidator implements ConstraintValidator<UniqueLogin,String> {
    private final UserRepository userRepo;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepo.existsByUserLogin(s);
    }
}
