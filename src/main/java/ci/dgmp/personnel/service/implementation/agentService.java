package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.email.service.interfac.EmailIservice;
import ci.dgmp.personnel.model.dao.AgentRepository;
import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.dto.mapper.AgentMapper;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.UserMapper;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.service.interfac.AgentIservice;
import ci.dgmp.personnel.service.interfac.ITokenService;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import ci.dgmp.personnel.service.validator.AgentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class agentService implements AgentIservice {
 private final AgentRepository agentRepository;
 private final AgentMapper agentMapper;
 private final AgentValidator agentValidator;
 private final UserMapper userMapper;
 private final UserRepository userRepo;
 private final StructureIservice strService;
 private final ITokenService tokenService;
 private final EmailIservice emailservice;

    @Override @Transactional
    public void saveAgent(AgentReqDto agentReqDto) throws IllegalAccessException {
        //agentValidator.validateCreate(agentReqDto);
       //if (agentRepository.findByAgtUserName(agentReqDto.getAgtUserName())!=null) throw new  AppException(ErrorMessage.AGENT_EXIST_USERNAME);
        Agent agent =new Agent();
        agent= agentMapper.mapToAgent(agentReqDto);
        agent.setTypeAgt(new Type(2L));
        //agent.setAgtDateNaissance(LocalDate.now());
        //int numYear= Calendar.getInstance().get(Calendar.YEAR)%100;//Recuperer les deux dernueres années
        int numYear= Calendar.getInstance().get(Calendar.YEAR);
        //String matricule=String.format("AGT-%02d-%03d",numYear,agent.getAgtId());
        //agent.setAgtMatricule(matricule);
        agent=agentRepository.save(agent);
        //Ajout d'un utilisateur
        AppUser user = userMapper.mapToUser(agentReqDto);
        user.setAgent(agent);
        user = userRepo.save(user);
        tokenService.generateToken(user);
        emailservice.sendActivationEmail(user);
    }

    @Override
    public Page<AgentResDto> getPageAgentsSearch(String critere, int page, int size) {
        List<AgentResDto> listAgentSerach =agentRepository.searchPageAgent(critere,PageRequest.of(page,size));
        return new PageImpl<>(listAgentSerach,PageRequest.of(page,size),agentRepository.count());
    }



    @Override
    public Page<AgentResDto> getAllPagesAgents(int page, int size) {
        List<AgentResDto>listeAllAgentsPage=agentRepository.getAllPagesAgents(PageRequest.of(page,size));
        return new PageImpl<>(listeAllAgentsPage,PageRequest.of(page,size),agentRepository.count());
    }

    @Override
    public List<AgentResDto> getAllAgentsByStructure(Long strId)
    {
        return strService.getAllSousStructure(strId).stream()
                .flatMap(str->agentRepository.findByStrId(str.getStrId()).stream())
                .map(agt->agentMapper.mapToAgentResponse(agt))
                .collect(Collectors.toList());
    }


}
