package ci.dgmp.personnel.email.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class EmailDto {
    private String username;
    private String email;
    private String token;
    private LocalDateTime sendingDate;
    private boolean seen;
    private boolean sent;
    private String mailObject;
    private String mailMessage;
    private Long senderUserId;
    private String senderUsername;
    private Long senderAgentId;
    private String systemMailSender;
}
