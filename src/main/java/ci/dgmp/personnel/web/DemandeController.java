package ci.dgmp.personnel.web;

import ci.dgmp.personnel.service.interfac.DemandeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/demandes")
@PreAuthorize("isAuthenticated()")
public class DemandeController {
    private final DemandeIservice demandeService;

    @GetMapping("/demande_autorisation_absence")
    public String indexAutorisationAbs(){
        return "pages/demande/autorisationAbsence/index";
    }
}
