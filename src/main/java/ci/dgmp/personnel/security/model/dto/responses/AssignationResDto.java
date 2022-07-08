package ci.dgmp.personnel.security.model.dto.responses;

import ci.dgmp.personnel.model.entities.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssignationResDto {
    private Long assId;
    private Date assDateDebut;
    private Date assDateFin;
    private boolean AssActive;
    private String structure;
}
