package ci.dgmp.personnel.security.web;

import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dao.RoleRepository;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToRoleReqDto;
import ci.dgmp.personnel.security.model.dto.request.RoleReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto;
import ci.dgmp.personnel.security.model.dto.responses.RoleResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.projection.PrivilegeToRoleInfo;
import ci.dgmp.personnel.security.service.interfac.PrivilegeToRoleIservice;
import ci.dgmp.personnel.security.service.interfac.RoleIservice;
import ci.dgmp.personnel.service.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/role")
public class RoleController {
    private final RoleIservice roleService;
    private final RoleRepository roleRepo;
    private final PrivilegeToRoleIservice ptrService;
    private final PrivilegeRepository privilegeRepo;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "critere",defaultValue = "") String critere,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size) {
        Page<RoleResDto>roles=critere.equals("")?roleService.getAllRoles(page, size):roleService.getAllRoles(critere, page, size);
        model.addAttribute("roles",roles);
        model.addAttribute("currentPage",page);
        model.addAttribute("critere", critere);
        model.addAttribute("pages", new int[roles.getTotalPages()]);
        model.addAttribute("role",new Role());
        return "admin/role/index";
    }

    @PostMapping(path = "/save")
    public String saveRole(RoleReqDto role){
        roleService.saveRole(role);
        Page<RoleResDto> roles=roleService.getAllRoles(0, 10);
        return "redirect:/admin/role/index";
    }

    @GetMapping("/privilleges")
    public String privilegeToRoleIndex(Model model, @RequestParam(name = "roleId") Long roleId){
        //Recuperation du roleId
        Role role =roleRepo.findById(roleId).orElseThrow(()->new AppException("Role introuvable"));
        List<PrivilegeToRoleInfo>privillesgeByRole=ptrService.getActivePrivilgeToRoleAss(role.getRoleId());
        model.addAttribute("privillesgeByRole", privillesgeByRole);
        model.addAttribute("role", role);
        List<PrivilegeResDto>privilleges=privilegeRepo.getAllPrivileges();
        model.addAttribute("privilleges",privilleges);
        PrivilegeToRoleReqDto ptr = new PrivilegeToRoleReqDto();
        ptr.setRoleId(roleId); // Récupration du roleId
        model.addAttribute("ptr",ptr);
        return "admin/role/privillege";

    }

    @PostMapping(path = "/savePrivilege")
    public String savePrivilege(PrivilegeToRoleReqDto privilege, RedirectAttributes ra){
        ptrService.addPrivillegeToRole(privilege);
        ra.addAttribute("roleId", privilege.getRoleId());
        return "redirect:/admin/role/privilleges";
    }

    //Revoquer un privilege a un utilisateur
    @GetMapping("/revokePrivilegeToRole")
    public String revokePrivilegeToRole(@Param("roleId") Long roleId, @Param("prvId") Long prvId, RedirectAttributes ra)
    {
        ptrService.revokePrivilegeToRole(roleId, prvId);
        ra.addAttribute("roleId", roleId);
        return "redirect:/admin/role/privilleges";
    }


}
