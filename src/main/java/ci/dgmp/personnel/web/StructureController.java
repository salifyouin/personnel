package ci.dgmp.personnel.web;

import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parametres/structure")
@PreAuthorize("isAuthenticated()")
public class StructureController {
    private final StructureIservice structureService;
    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/index")
    public String index(Model model){
        return "pages/structure/index";
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/indexPere")
    public String indexPere(Model model,
                            @RequestParam(name = "critere",defaultValue = "") String critere,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "6") int size){
        Page<StructureResDto> listeStructures=critere.equals("") ?
                structureService.getAllPagesStructures(page,size):structureService.searchPageStructures(critere, page, size);
        model.addAttribute("listeStructures",listeStructures);
        model.addAttribute("currentPage", page);
        model.addAttribute("critere",critere);
        model.addAttribute("pages", new int[listeStructures.getTotalPages()]);
        return "pages/structure/indexStructurePere";
    }
}
