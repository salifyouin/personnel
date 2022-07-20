package ci.dgmp.personnel.email.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Email
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailId;
    private String username;
    private String email; //Recipient Email
    private String token;
    private LocalDateTime sendingDate;
    private boolean seen;
    private boolean sent;
    private String mailObject;
    private String mailMessage;

    private Long senderUserId;
    private String senderUsername;
    private Long senderFunctionId;
    private String senderFunctionCode;
    private String senderFunctionName;
    private Long senderAgentId;

    private String systemMailSender; /* L'adresse mail utilisée pour envoyer le mail*/
}
