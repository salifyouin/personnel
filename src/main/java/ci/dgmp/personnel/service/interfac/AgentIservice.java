package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.dto.AgentResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentIservice {
    void saveAgent(AgentReqDto agent);
    Page<AgentResDto> getPageAgentsSearch(String critere,int page, int size);

    Page<AgentResDto> getAllPagesAgents(int page, int size);

    List<AgentResDto> getAllAgentsByStructure(Long strId);
}
