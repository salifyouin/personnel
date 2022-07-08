package ci.dgmp.personnel.security.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleReqDto {
    private String roleCode;
    private String roleName;
    private boolean roleActif;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
