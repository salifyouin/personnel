package ci.dgmp.personnel.security.model.dto.responses;

import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrivilegeToRoleResDto {
    private Long privilegeId;
    private String privilegeCode;
    private String privilegeName;
    private Long roleId;
    private String roleCode;
    private String roleName;
    private Date assDateDebut;
    private Date assDateFin;
    private boolean assActive;
    private Long assStrId;
    private String strSigle;
    private String strLibelle;

    public PrivilegeToRoleResDto (PrivilegeToRole privilegeToRole) {
        BeanUtils.copyProperties(privilegeToRole, this);
        this.setPrivilegeId(privilegeToRole.getPrivilege().getPrivilegeId());
        this.setRoleId(privilegeToRole.getRole().getRoleId());
    }
}
