package ci.dgmp.personnel.security.model.entities;

import ci.dgmp.personnel.model.entities.Demande;
import ci.dgmp.personnel.model.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;
    private String imgNom;
    private String imgChemin;
    @ManyToOne
    @JoinColumn(name = "img_userId")
    private AppUser appUser;
    @Transient
    private MultipartFile file;
}
