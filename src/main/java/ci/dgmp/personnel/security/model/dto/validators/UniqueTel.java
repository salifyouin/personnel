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
@Constraint(validatedBy = {UniqueTelValidator.class})
@Documented
public @interface UniqueTel {
    String message() default "Le numero de telephone existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component
@RequiredArgsConstructor
class UniqueTelValidator implements ConstraintValidator<UniqueTel,String>
{
    private final UserRepository userRepo;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepo.existsByUserTelephone(s);
    }
}
