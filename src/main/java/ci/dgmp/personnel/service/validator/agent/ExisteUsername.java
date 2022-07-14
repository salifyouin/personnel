package ci.dgmp.personnel.service.validator.agent;

import ci.dgmp.personnel.model.dao.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})//Sur quel type d'element l'anotation est utilisée
@Retention(RetentionPolicy.RUNTIME)//a l'execurion
@Constraint(validatedBy = {ExisteUsernameValidator.class})
public @interface ExisteUsername {
    String message() default "Ce nom utilisateur existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};
}
@Component @RequiredArgsConstructor
class ExisteUsernameValidator implements ConstraintValidator <ExisteUsername,String>{
    private final AgentRepository agtRepo;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //Si l'utilisateur n'esxiste pas ramene true
        return !agtRepo.existsByAgtUserName(s);
    }
}


