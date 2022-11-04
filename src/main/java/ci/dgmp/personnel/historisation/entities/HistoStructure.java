package ci.dgmp.personnel.historisation.entities;

import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class HistoStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hisId;
    private Long strId;
    private String strCode;
    private String strSigle;
    private String strLibelle;
    private long strLevel;
    private Long  strTypId;
    private long tutelleDirecteId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Embedded
    private HistoData histoData;
    private String adresseIp;
    private byte[] adresseMac;
    private String hostName;

}
