package ci.dgmp.personnel.web;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parametres/structure")
@PreAuthorize("isAuthenticated()")
public class StructureController {
    private final StructureIservice structureService;
    private final StructureRepository typeRepo;

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

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/add")
    public String addStrForm(Model model){
        model.addAttribute("typesStr", structureService.getTypesStructures());
        model.addAttribute("strDto", new StructureReqDto());
        return "pages/structure/addStructure";
    }

    @ResponseBody
    @GetMapping("/getStrMereByTypeLevel")
    public List<Structure> getStrMereByTypeLevel(@RequestParam Long typId)
    {
        return typeRepo.getStrMereByTypeId(typId);
    }

   // @PreAuthorize("hasAnyAuthority('RESPONSABLE RH','DEV')")
    @PostMapping(path = "/saveStructure")
    public String saveStructure(StructureReqDto structure){
      structureService.saveStructure(structure);
      return "redirect:/parametres/structure/index";
    }

}
