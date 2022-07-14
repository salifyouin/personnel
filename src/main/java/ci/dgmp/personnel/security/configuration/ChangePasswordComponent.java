package ci.dgmp.personnel.security.configuration;

import ci.dgmp.personnel.security.model.dto.request.ChangePasswordDto;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("cpc") @Scope("session") @Data
public class ChangePasswordComponent
{
    private ChangePasswordDto dto;
}
