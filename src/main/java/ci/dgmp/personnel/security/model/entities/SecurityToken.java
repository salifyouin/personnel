package ci.dgmp.personnel.security.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class SecurityToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String token;
    private LocalDateTime tokenExpirationDate;
    @ManyToOne @JoinColumn(name = "TokenUserId")
    private AppUser appUser;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
