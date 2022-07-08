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
@Constraint(validatedBy = {NoneExistingPrivilegeName.NoneExistingPrivilegeNameValidator.class})
@Documented
public @interface NoneExistingPrivilegeName
{
    String message() default "Ce privilege existe déja";
    Class<?>[] groups() default {};
    Class<? extends Payload>  [] payload() default {};

    @Component @RequiredArgsConstructor
    class NoneExistingPrivilegeNameValidator implements ConstraintValidator<NoneExistingPrivilegeName,String>
    {
        private final PrivilegeRepository privilegeRepo;
        @Override
        public boolean isValid(String privilegeName, ConstraintValidatorContext constraintValidatorContext) {
            //Verifier si l'utilisateur n'existe pas ramene true
            return !privilegeRepo.existsByPrivilegeName(privilegeName);

        }

        @Override
        public void initialize(NoneExistingPrivilegeName constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }
    }

}


