package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistingPrivilegeId.ExistingPrivilegeIdValidator.class})
@Documented
public @interface ExistingPrivilegeId
{
    String message() default "PrivilegeId introuvable";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};

    @Component @RequiredArgsConstructor
    class ExistingPrivilegeIdValidator implements ConstraintValidator<ExistingPrivilegeId, Long>
    {
        private final PrivilegeRepository prvRepo;
        @Override
        public boolean isValid(Long value, ConstraintValidatorContext context)
        {
            return prvRepo.existsById(value);
        }
    }
}

