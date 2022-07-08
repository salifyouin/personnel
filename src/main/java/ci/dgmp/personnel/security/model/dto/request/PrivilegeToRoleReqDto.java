package ci.dgmp.personnel.security.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrivilegeToRoleReqDto
{
    private Long privilegeId;
    private Long roleId;
    @Temporal(TemporalType.DATE) // Pour JPA. Signifie que dans la BD la date aura le type Date et non le type TimeStamp
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Spring formate la date pour nous
    private LocalDate assDateDebut;
    @Temporal(TemporalType.DATE) // Pour JPA. Signifie que dans la BD la date aura le type Date et non le type TimeStamp
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Spring formate la date pour nous
    private LocalDate assDateFin;
    private String assActive;
    private Long assStrId;
}
