package ci.dgmp.personnel.security.model.dto.responses;

import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.Role;
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
public class RoleResDto {
    private long roleId;
    private String roleCode;
    private String roleName;
    private boolean roleActif;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String critere;

    public RoleResDto (Role role) {
        BeanUtils.copyProperties(role, this);

    }
}
