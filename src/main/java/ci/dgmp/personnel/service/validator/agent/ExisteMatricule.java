package ci.dgmp.personnel.service.validator.agent;

import ci.dgmp.personnel.model.dao.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExisteMatriculeValidator.class})
@Documented
public @interface ExisteMatricule {
    String message() default "Ce numero matricule existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component @RequiredArgsConstructor
class ExisteMatriculeValidator implements ConstraintValidator<ExisteMatricule,String> {
   private final AgentRepository agtRepo;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !agtRepo.existsByAgtMatricule(s);
    }
}
