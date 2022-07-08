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
public class UserReqDto {
    private String userNom;
    private String userPrenom;
    private String userLogin;
    private String userPassword;
    private String userTelephone;
    private String userEmail;
    private boolean userActive;
    private Long agtId;
    private String agtMatricule;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
