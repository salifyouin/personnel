package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dto.request.ChangePasswordDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ConfirmPassword.ConfirmPasswordValidator.class, ConfirmPassword.ConfirmPasswordValidator2.class})
@Documented
public @interface ConfirmPassword
{
    String message() default "userConfirmPassword:Les deux mots de passe doivent être identiques";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Component @RequiredArgsConstructor
    class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, UserReqDto>
    {
        private final PrivilegeRepository prvRepo;
        @Override
        public boolean isValid(UserReqDto dto, ConstraintValidatorContext context)
        {
            return dto.getUserPassword().equals(dto.getUserConfirmPassword());
        }
    }

    @Component @RequiredArgsConstructor
    class ConfirmPasswordValidator2 implements ConstraintValidator<ConfirmPassword, ChangePasswordDto>
    {
        private final PrivilegeRepository prvRepo;
        @Override
        public boolean isValid(ChangePasswordDto dto, ConstraintValidatorContext context)
        {
            return dto.getUserPassword().equals(dto.getUserConfirmPassword());
        }
    }
}

