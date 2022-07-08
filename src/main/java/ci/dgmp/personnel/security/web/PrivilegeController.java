package ci.dgmp.personnel.security.web;

import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.service.interfac.PrivilegeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/privillege")
public class PrivilegeController {
    private final PrivilegeIservice privilegeService;
    private final PrivilegeRepository privilegeRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "critere",defaultValue = "") String critere,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        Page<PrivilegeResDto> privileges=critere.equals("")?privilegeService.getAllPrivileges(page, size)
                :privilegeService.getAllPrivileges(critere,page,size);
        model.addAttribute("privileges",privileges);
        model.addAttribute("currentPage",page);
        model.addAttribute("critere", critere);
        model.addAttribute("pages", new int[privileges.getTotalPages()]);
        model.addAttribute("privilege",new Privilege());
        return "admin/privilege/index";
    }

    @PostMapping(path = "/save")
    public String savePrivilege(@Valid Privilege privilege, BindingResult br, Model model){
        if(br.hasErrors())
        {
            br.getFieldErrors().forEach(err->model.addAttribute(err.getField()+"Error", err.getDefaultMessage()));
            model.addAttribute("hasError", 1);
            return index(model, "", 0, 10);
        }
        privilegeService.savePrivilege(privilege);
        Page<PrivilegeResDto> privileges=privilegeService.getAllPrivileges(0, 2);
       return "redirect:/admin/privillege/index";
    }



}
