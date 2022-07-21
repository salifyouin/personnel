package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.SecurityTokenRepo;
import ci.dgmp.personnel.security.model.dto.request.ActiveUserAccountDto;
import ci.dgmp.personnel.security.model.dto.request.ResetUserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailAndTokenExist.EmailAndTokenExistValidator.class, EmailAndTokenExist.EmailAndTokenExistValidatorForResetPassword.class})
@Documented
public @interface EmailAndTokenExist
{
    String message() default "Lien d'activation invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Component
    @RequiredArgsConstructor
    class EmailAndTokenExistValidator implements ConstraintValidator<EmailAndTokenExist, ActiveUserAccountDto>
    {
        private final SecurityTokenRepo stkRepo;
        @Override
        public boolean isValid(ActiveUserAccountDto dto, ConstraintValidatorContext context)
        {
            String email = dto.getUserEmail();
            String token = dto.getToken();
            return stkRepo.existsByTokenAndEmail(token, email) ;
        }
    }

    @Component
    @RequiredArgsConstructor
    class EmailAndTokenExistValidatorForResetPassword implements ConstraintValidator<EmailAndTokenExist, ResetUserAccountDto>
    {
        private final SecurityTokenRepo stkRepo;
        @Override
        public boolean isValid(ResetUserAccountDto dto, ConstraintValidatorContext context)
        {
            String email = dto.getUserEmail();
            String token = dto.getToken();
            return stkRepo.existsByTokenAndEmail(token, email) ;
        }
    }
}
