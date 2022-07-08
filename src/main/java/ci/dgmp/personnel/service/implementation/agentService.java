package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.AgentRepository;
import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.dto.mapper.AgentMapper;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.UserMapper;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.service.interfac.AgentIservice;
import ci.dgmp.personnel.service.validator.AgentValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class agentService implements AgentIservice {
 private final AgentRepository agentRepository;
 private final AgentMapper agentMapper;
 private final AgentValidator agentValidator;
 private final UserMapper userMapper;
 private final UserRepository userRepo;

    @Override @Transactional
    public void saveAgent(AgentReqDto agentReqDto) {
        //agentValidator.validateCreate(agentReqDto);
        Agent agent =new Agent();
        agent= agentMapper.mapToAgent(agentReqDto);
        agent.setTypeAgt(new Type(2L));
        //agent.setAgtDateNaissance(LocalDate.now());
        //int numYear= Calendar.getInstance().get(Calendar.YEAR)%100;//Recuperer les deux dernueres années
        int numYear= Calendar.getInstance().get(Calendar.YEAR);
        String matricule=String.format("AGT-%02d-%03d",numYear,agent.getAgtId());
        agent.setAgtMatricule(matricule);
        agent=agentRepository.save(agent);
        //Ajout d'un utilisateur
        AppUser user = userMapper.mapToUser(agentReqDto);
        user.setAgent(agent);
        userRepo.save(user);
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



}
