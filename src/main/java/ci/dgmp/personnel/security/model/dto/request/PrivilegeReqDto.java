package ci.dgmp.personnel.security.model.dto.request;

import ci.dgmp.personnel.security.model.dto.validators.NoneExistingPrivilegeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrivilegeReqDto {
    private String privilegeCode;
    private String privilegeName;
    private boolean privilegeActif;
    private LocalDateTime privilegeCretatedAt;
    private LocalDateTime privilegeUpdatedAt;
}
