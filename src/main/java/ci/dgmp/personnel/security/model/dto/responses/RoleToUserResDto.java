package ci.dgmp.personnel.security.model.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleToUserResDto {
    private Long roleId;
    private String roleCode;
    private String roleName;
    private Long userId;
    private String userName;
    private String userTelephone;
    private String userActive;
    private Long agtId;
    private String agtMatricule;
    private Date assDateDebut;
    private Date assDateFin;
    private boolean assActive;
    private Long assStrId;
    private String strSigle;
    private String strLibelle;
}
