package ci.dgmp.personnel.web;

import ci.dgmp.personnel.service.interfac.TraitementIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("traitement")
public class TraitementController {
private final TraitementIService traitementService;
    @PostMapping()
    public String index(){
        return "pages/metier/traitement/index";
    }
}
