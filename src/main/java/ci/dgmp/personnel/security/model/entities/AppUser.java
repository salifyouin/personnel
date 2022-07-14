package ci.dgmp.personnel.security.model.entities;

import ci.dgmp.personnel.model.entities.Agent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userNom;
    private String userPrenom;
    private String userLogin;
    private String userPassword;
    private String userTelephone;
    private String userEmail;
    private boolean userActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne @JoinColumn(name = "agtId")
    private Agent agent;

    public AppUser(Long userId) {
        this.userId = userId;
    }
}
