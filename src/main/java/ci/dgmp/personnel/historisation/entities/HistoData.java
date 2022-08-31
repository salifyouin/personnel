package ci.dgmp.personnel.historisation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HistoData
{
    private Long agtId;
    private String agtNom;
    private String agtPrenom;
    private Long userId;
    private String userNom;
    private String userLogin;
    private String userPrenom;
    private String action;
}
