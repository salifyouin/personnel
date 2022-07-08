package ci.dgmp.personnel.security.model.dto.request;

import ci.dgmp.personnel.security.model.enums.AssType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssignationReqDto
{
    private Long userId;
    private Long privilegeId;
    private Long roleId;
    private Date assDateDebut;
    private Date assDateFin;
    private String AssActive;
    private Long assStrId;
    private String type;
}
