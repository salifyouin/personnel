package ci.dgmp.personnel.model.dto;

import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StructureResDto {
    private Long strId;
    private String strCode;
    private String strSigle;
    private String strLibelle;
    private Long strStrId;
    private Long strtypId;
    private String typStrLibelle;
    private Boolean strStatut;
    private long strLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String critere;

    public StructureResDto(Structure structure) {
        BeanUtils.copyProperties(structure, this);
        this.strStrId=structure.getTutelleDirecte()==null?null:structure.getTutelleDirecte().getStrId()==null ? 0: structure.getTutelleDirecte().getStrId();
        this.setStrtypId(structure.getType().getTypId()==null ? 0:structure.getType().getTypId());
    }
}
