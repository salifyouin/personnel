package ci.dgmp.personnel.service.validator;

import ci.dgmp.personnel.model.dao.AgentRepository;
import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.service.exception.AppException;
import ci.dgmp.personnel.service.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentValidator{
    private final AgentRepository agentRepository;
    public void validateCreate(AgentReqDto agent) {
        if(agent.getAgtMatricule()==null || agent.getAgtMatricule().trim().equalsIgnoreCase("")) throw new AppException(ErrorMessage.AGENT_MATTRICULE_VIDE);
        if(agent.getAgtNom()==null || agent.getAgtNom().trim().equalsIgnoreCase("")) throw new AppException(ErrorMessage.AGENT_NOM_VIDE);
        if(agent.getAgtPrenom()==null || agent.getAgtPrenom().trim().equalsIgnoreCase("")) throw new AppException(ErrorMessage.AGENT_PRENOM_VIDE);
    }
}
