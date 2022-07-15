package ci.dgmp.personnel.security.model.dto.validators;

import ci.dgmp.personnel.security.model.dao.SecurityTokenRepo;
import ci.dgmp.personnel.security.model.dto.request.ActiveUserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NoneExpiredToken.NoneExpiredTokenValidator.class})
@Documented
public @interface NoneExpiredToken
{
    String message() default "Le lien d'activation a expiré";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Component
    @RequiredArgsConstructor
    class NoneExpiredTokenValidator implements ConstraintValidator<NoneExpiredToken, ActiveUserAccountDto>
    {
        private final SecurityTokenRepo stkRepo;
        @Override
        public boolean isValid(ActiveUserAccountDto dto, ConstraintValidatorContext context)
        {
            return stkRepo.tokenIsNotExpired(dto.getToken());
        }
    }
}
