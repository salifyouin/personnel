package ci.dgmp.personnel.historisation.dto.request;

import ci.dgmp.personnel.model.entities.Structure;
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
public class HistoStructureReq {
    private Long strId;
    private String strCode;
    private String strSigle;
    private String strLibelle;
    private long strLevel;
    private long strtypId;
    private long tutelleDirecte;
    private Long agtId;
    private String agtNom;
    private String agtPrenom;
    private String action;
    private LocalDateTime createdAt;
}
