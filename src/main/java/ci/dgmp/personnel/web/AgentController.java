package ci.dgmp.personnel.web;

import ci.dgmp.personnel.model.dao.AgentRepository;
import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dao.TypeRepository;
import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.security.service.implementation.SecurityContextService;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import ci.dgmp.personnel.service.interfac.AgentIservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j

@RequestMapping("/agent")
@PreAuthorize("isAuthenticated()")//Acces a la page necessite une authentification

public class AgentController {
    private final AgentIservice agentservice;
    private final AgentRepository agentRepository;
    private final TypeRepository typeRepository;
    private final StructureRepository structureRepository;
    private final SecurityContextService scs;
    private final IAuthorityService authService;

    @PreAuthorize("hasAnyAuthority('RESPONSABLE RH','DEV')")
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "critere",defaultValue = "") String critere,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "2") int size){
        Page<AgentResDto> listAgents=critere.equals("") ? agentservice.getAllPagesAgents(page,size):agentservice.getPageAgentsSearch(critere, page, size);
        model.addAttribute("listAgents", listAgents);
        model.addAttribute("currentPage", page);
        model.addAttribute("critere", critere);
        model.addAttribute("pages", new int[listAgents.getTotalPages()]);
        return "pages/agent/index";
    }

    @PreAuthorize("hasAnyAuthority('RESPONSABLE RH','DEV')")
    @GetMapping("/index2")
    public String index2(Model model, @RequestParam(name = "critere",defaultValue = "") String critere){
        //List<AgentResDto> listAgents=critere.equals("") ? agentservice.getAllPagesAgents(page,size):agentservice.getPageAgentsSearch(critere, page, size)
        Long strId = authService.getActiveRoleAssForUser(scs.getAuthUser().getUserId()).getStructure().getStrId();
        List<AgentResDto> listAgents=agentservice.getAllAgentsByStructure(strId);
        model.addAttribute("listAgents", listAgents);
        //model.addAttribute("currentPage", page);
        model.addAttribute("critere", critere);
        //model.addAttribute("pages", new int[listAgents.getTotalPages()]);
        return "pages/agent/index2";
    }



    @PreAuthorize("hasAnyAuthority('RESPONSABLE RH','DEV')")
    @GetMapping("/addAgent")
    public String form(Model model){
        model.addAttribute("agent", new AgentReqDto());
        model.addAttribute("typeFonction",typeRepository.findTypeByType_TypId(4L));
        model.addAttribute("structures",structureRepository.findStructureByTutelleDirecte(2L));
        model.addAttribute("grades",typeRepository.findTypeByType_TypId(17L));
        model.addAttribute("structuresParent",structureRepository.findStructureByTutelleDirecteIsNull());
        //model.addAttribute("structures",structureRepository.findStructureByStructureStrId(structureRepository.findById()));
        //model.addAttribute("fonctions", fonctionservice.getFonctions());
        return "pages/agent/addAgent";
    }

    @PreAuthorize("hasAnyAuthority('RESPONSABLE RH','DEV')")
    @PostMapping(path = "/saveAgent")
    public String saveAgent(@Valid AgentReqDto agent, BindingResult result, Model model, RedirectAttributes ra){
        if(result.hasErrors()) {
            System.out.println("Erreuuuuuuuuuuuuuuuuuuuuuuuuuuur");
            result.getAllErrors().forEach(err->log.info(err.toString()));
            model.addAttribute("agent", agent);
            return "pages/agent/addAgent";
        }
         agentservice.saveAgent(agent);
        ra.addFlashAttribute("message",
                "Enregistrement éffectué avec succès" );
        return "redirect:/agent/index";
    }

    public String deleteAgent(Long userId){
        return "redirect:/agent/index";
    }


}
