package ci.dgmp.personnel.web;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.dto.TreeviewDto;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parametres/structure")
@PreAuthorize("isAuthenticated()")
public class StructureController {
    private final StructureIservice strService;
    private final StructureRepository typeRepo;
    private final ISecurityContextService scs;
    private final IAuthorityService authService;

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0") Long strId)
    {
        RoleToUser rtuAss = authService.getActiveRoleAssForUser(scs.getAuthUser().getUserId());
        //model.addAttribute("strId", strId);
        model.addAttribute("strId", rtuAss == null ? 0 : rtuAss.getStructure() == null ? 0 : rtuAss.getStructure().getStrId());
        return "pages/structure/index";
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/indexPere")
    public String indexPere(Model model,
                            @RequestParam(name = "critere",defaultValue = "") String critere,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "6") int size){
        Page<StructureResDto> listeStructures=critere.equals("") ?
                strService.getAllPagesStructures(page,size): strService.searchPageStructures(critere, page, size);
        model.addAttribute("listeStructures",listeStructures);
        model.addAttribute("currentPage", page);
        model.addAttribute("critere",critere);
        model.addAttribute("pages", new int[listeStructures.getTotalPages()]);
        return "pages/structure/indexStructurePere";
    }

    @GetMapping(path = "/str-treeview")
    @ResponseBody
    public List<TreeviewDto> getTreeviewDtos(@RequestParam Long strId)
    {
        if(strId.longValue() == 0) return strService.getTreeviewRoot();
        return strService.getTreeviewDtos(strId);
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/add")
    public String addStrForm(Model model){
        model.addAttribute("typesStr", strService.getTypesStructures());
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
    public String saveStructure(StructureReqDto structure) throws UnknownHostException {
      strService.saveStructure(structure);
      return "redirect:/parametres/structure/index";
    }


    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/historique")
    public String historique(){
        return "pages/structure/historique";
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/detail")
    public String detail(){
        return "pages/structure/detail";
    }

}
