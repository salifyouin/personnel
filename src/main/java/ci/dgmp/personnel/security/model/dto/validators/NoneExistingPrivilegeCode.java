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
@Constraint(validatedBy = {NoneExistingPrivilegeCode.ExistingPrivilegeIdValidator.class})
@Documented
public @interface NoneExistingPrivilegeCode
{
    String message() default "PrivilegeCode existe deja";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Component @RequiredArgsConstructor
    class ExistingPrivilegeIdValidator implements ConstraintValidator<NoneExistingPrivilegeCode, String>
    {
        private final PrivilegeRepository prvRepo;
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context)
        {
            return !prvRepo.existsByPrivilegeCode(value);
        }
    }
}

