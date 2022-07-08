package ci.dgmp.personnel.model.dto;

import ci.dgmp.personnel.model.entities.Structure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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
    private String critere;

    public StructureResDto(Structure structure) {
        BeanUtils.copyProperties(structure, this);
        this.strStrId=structure.getTutelleDirecte().getStrId()==null ? 0: structure.getTutelleDirecte().getStrId();
        this.setStrtypId(structure.getType().getTypId()==null ? 0:structure.getType().getTypId());
    }
}
