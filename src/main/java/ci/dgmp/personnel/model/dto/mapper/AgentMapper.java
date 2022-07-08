package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgentMapper {
    public AgentResDto mapToAgentResponse(Agent agent) {
        AgentResDto agentResDto = new AgentResDto();
        BeanUtils.copyProperties(agent, agentResDto);
//        agentResDto.setAgtFonId(agent.getFonction().getFonId());
//        agentResDto.setFonction(agent.getFonction().getFonLibelle());
        agentResDto.setAgtStrId(agent.getStructure().getStrId());
        agentResDto.setStructure(agent.getStructure().getStrLibelle());
        agentResDto.setAgtFonId(agent.getFonction().getTypId());
        agentResDto.setFonction(agent.getFonction().getTypLibelle());
        agentResDto.setAgtId(agent.getTypeAgt().getTypId());
        agentResDto.setTypeAgent(agent.getTypeAgt().getTypLibelle());
        agentResDto.setAgtGradeId(agent.getGrade().getTypId());
        agentResDto.setAgtGradeLibelle(agent.getGrade().getTypLibelle());
        return agentResDto;
    }

    public Agent mapToAgent(AgentReqDto agentReqDto) {
        Agent agent = new Agent();
        BeanUtils.copyProperties(agentReqDto, agent);
        agent.setFonction(agentReqDto.getAgtFonId()==null ? null:new Type(agentReqDto.getAgtFonId()));
        agent.setStructure(agentReqDto.getAgtStrId()==null ? null : new Structure(agentReqDto.getAgtStrId()));
        agent.setTypeAgt(agentReqDto.getAgtTypId()==null ? null : new Type(agentReqDto.getAgtTypId()));
        agent.setGrade(agentReqDto.getAgtGradeId()==null ? null : new Type(agentReqDto.getAgtGradeId()));
        agent.setCreatedAt(LocalDateTime.now());
        agent.setUpdatedAt(LocalDateTime.now());
        return agent;
    }
}
