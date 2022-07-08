package ci.dgmp.personnel.security.model.dto.responses;

import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.security.model.entities.Privilege;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrivilegeResDto {
    private Long privilegeId;
    private String privilegeCode;
    private String privilegeName;
    private boolean privilegeActif;
    private LocalDateTime privilegeCretatedAt;
    private LocalDateTime privilegeUpdatedAt;
    private String critere;

    public PrivilegeResDto (Privilege privilege) {
        BeanUtils.copyProperties(privilege, this);

    }
}
