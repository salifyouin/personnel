package ci.dgmp.personnel.model.dto;

import ci.dgmp.personnel.model.entities.Agent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AgentResDto {
    private Long agtId;
    private String agtMatricule;
    private String agtNom;
    private String agtPrenom;
    private LocalDateTime agtDateNaissance;
    private String agtTel;
    private String agtAdresse;
    private String agtSituationMat;
    private String agtUserName;
    private String agtPassword;
    private Boolean agtActif;
    private Long agtStrId;
    private Long agtFonId;
    private Long agtTypId;
    private Long agtGradeId;
    private String agtGradeLibelle;
    private String structure;
    private String fonction;
    private String typeAgent;
    private String critere;

    public AgentResDto (Agent agent) {
        BeanUtils.copyProperties(agent, this);
        this.fonction =agent.getFonction()==null ? "": agent.getFonction().getTypLibelle();
        this.agtFonId=agent.getFonction()==null ? 0: agent.getFonction().getTypId();
        this.agtStrId=agent.getStructure()==null ? 0: agent.getStructure().getStrId();
        this.structure=agent.getStructure()==null ? "": agent.getStructure().getStrLibelle();
        this.agtTypId=agent.getTypeAgt()==null ? 0: agent.getTypeAgt().getTypId();
        this.agtGradeId=agent.getGrade()==null ? 0 : agent.getGrade().getTypId();
        this.agtGradeLibelle=agent.getGrade()==null ?"" : agent.getGrade().getTypLibelle();
        this.structure=agent.getStructure()==null ? "": agent.getStructure().getStrLibelle();
    }
}
