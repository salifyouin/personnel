package ci.dgmp.personnel.security.model.dto.request;

import ci.dgmp.personnel.security.model.dto.validators.ExistingPrivilegeId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrivilegeToUserReqDto
{
    @NotNull(message = "PrivilegeId non fourni")
    @ExistingPrivilegeId
    private Long privilegeId;
    @NotNull(message = "userId non fourni")
    private Long userId;
    @FutureOrPresent(message = "La date de début ne peut être une date passée")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate assDateDebut;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @FutureOrPresent(message = "La date de fin ne peut être une date passée")
    private LocalDate assDateFin;
    private String AssActive;
    @NotNull(message = "strId non fourni")
    private Long assStrId;
}
