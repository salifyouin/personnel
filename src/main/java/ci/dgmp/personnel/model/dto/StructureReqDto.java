package ci.dgmp.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StructureReqDto {
    private Long strId;
    private String strCode;
    private String strSigle;
    private String strLibelle;
    private Long strStrId;
    private Long strtypId;
    private String typStrLibelle;

//    public StructureResDto(Agent agent) {
//        BeanUtils.copyProperties(agent, this);
//        this.fonction =agent.getFonction()==null ? "": agent.getFonction().getTypLibelle();
//        this.agtFonId=agent.getFonction()==null ? 0: agent.getFonction().getTypId();
//        this.agtStrId=agent.getStructure()==null ? 0: agent.getStructure().getStrId();
//        this.structure=agent.getStructure()==null ? "": agent.getStructure().getStrLibelle();
//        this.agtTypId=agent.getTypeAgt()==null ? 0: agent.getTypeAgt().getTypId();
//        this.agtGradeId=agent.getGrade()==null ? 0 : agent.getGrade().getTypId();
//        this.agtGradeLibelle=agent.getGrade()==null ?"" : agent.getGrade().getTypLibelle();
//        this.structure=agent.getStructure()==null ? "": agent.getStructure().getStrLibelle();
//    }
}
