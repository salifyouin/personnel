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
@Constraint(validatedBy = {MailExistvalidator.class})
@Documented
public @interface MailExist {
    String message() default "Cette adresse email existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component @RequiredArgsConstructor
class  MailExistvalidator implements ConstraintValidator<MailExist,String> {
private final AgentRepository agtRepo;
    @Override
    public void initialize(MailExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !agtRepo.existsByAgtAdresse(s);
    }
}
