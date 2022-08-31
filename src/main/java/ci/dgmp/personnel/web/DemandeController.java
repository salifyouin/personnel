package ci.dgmp.personnel.web;

//import ci.dgmp.personnel.service.interfac.DemandeIservice;
import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.entities.Absence;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.service.implementation.SecurityContextService;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import ci.dgmp.personnel.service.interfac.AbsenceIservice;
import ci.dgmp.personnel.service.utilitaires.AuthAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/demande")
@PreAuthorize("isAuthenticated()")
public class DemandeController {
    private final AbsenceIservice absenceService;
    private final SecurityContextService scs;
    private final IAuthorityService authService;
    private final AuthAgent authAgent;
    @GetMapping("/absence/index")
    public String indexAbsence(Model model){
        Long agtId = authService.getActiveRoleAssForUser(scs.getAuthUser().getUserId()).getAppUser().getAgent().getAgtId();
        List<Absence>absences=absenceService.findDemandeAbsence(agtId);
        model.addAttribute("absences", absences);
        return "pages/demande/absence/index";
    }

    @GetMapping("/absence/addAbsence")
    public String absenceAddForm(Model model){
        model.addAttribute("absence",new Absence());
        return "pages/demande/absence/addAbsence";
    }

    //@PreAuthorize("hasAnyAuthority('RESPONSABLE RH','DEV')")
    @PostMapping(path = "/absence/saveAbsence")
    public String saveAbsence(Absence absence) {
        absenceService.saveAbsence(absence);
        return "redirect:/demande/absence/index";
    }

}
