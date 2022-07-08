package ci.dgmp.personnel.security.web;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.projection.StructureInfo;
import ci.dgmp.personnel.security.model.dao.*;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.*;
import ci.dgmp.personnel.security.model.projection.*;
import ci.dgmp.personnel.security.service.interfac.*;
import ci.dgmp.personnel.service.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/utilisateur")
@RequiredArgsConstructor
public class UserController {
    private final UserIservice userService;
    private final RoleToUserIservice rtoService;
    private final RoleToUserRepository rtuRepo;
    private final UserRepository userRepo;
    private final RoleIservice roleService;
    private final RoleToUserIservice rtsService;
    private final StructureRepository srtRepo;
    private final PrivilegeRepository privRepo;
    private final PrivilegeToUserIservice ptuService;
    private final PrivilegeToUserRepository ptuRepo;
    private final PrivilegeIservice privilegeService;
    @GetMapping("/index")
    public String index(Model model) {
        List<AppUserInfo> users=userService.getAllUsers();
        model.addAttribute("users",users);
        return "admin/user/index";
    }

    @GetMapping("/rolePrivilege")
    public String rolePrivilegeIndex(Model model, @RequestParam(name = "userId") Long userId) {
        //Recuperation de l'id de l'utilisateur depuis la table AppUser
        AppUser user=userRepo.findById(userId).orElseThrow(()->new AppException("Utilisateur introuvable"));
        //Stocker l'utilisateur dans l'objet user
        model.addAttribute("user", user);
        //je passe l'id recuperé dans a la projection
        List<RoleToUserInfo>rolesByUser=rtoService.getRoleByUser(user.getUserId());
        RoleToUser activeAss = rtuRepo.getActiveAssignationForUser(userId);

        //je cree un objet de la liste avec l'id passé en parametre
        model.addAttribute("rolesByUser", rolesByUser);
        //Combobox role pour assignation de role a un utilisateur
        List<RoleInfo>roles=roleService.getAllRoles();
        model.addAttribute("roles",roles);
        //Combobo structure
        List<StructureInfo>structures=srtRepo.getAllStructureFille();
        model.addAttribute("structures",structures);
        //On recupere l'id du user pour passer a au dto pour l'enregistrement
        RoleToUserReqDto rtu=new RoleToUserReqDto();
        rtu.setUserId(userId);
        model.addAttribute("rtu",rtu);


        //Privilèges de privilège a l'utilisateur
        //On recupere l'id du user et passer au DTO
        PrivilegeToUserReqDto ptu=new PrivilegeToUserReqDto();
        ptu.setUserId(userId);
        model.addAttribute("ptu",ptu);
        //combobox privilège
        List<PrivilegeInfo>privileges=privRepo.getAllPrivilegess();
        model.addAttribute("privileges",privileges);
        //Afficher la liste des priviles de l'utilisateur
        List<PrivilegeToUserInfo> privilegesByUser=ptuRepo.getPrivilegeByUser(user.getUserId());
        //Afficher privileges du role actif de l'utilisateur dans une structure
        List<Privilege> userPrivileges = activeAss == null ? new ArrayList<>() : privilegeService.getUserPrivileges(userId, activeAss.getStructure().getStrId());

        model.addAttribute("userId", userId);
        model.addAttribute("strId", activeAss==null?null:activeAss.getStructure().getStrId());
        model.addAttribute("userPrivileges",userPrivileges);
        model.addAttribute("privilegesByUser",privilegesByUser);
        return "admin/user/rolePrivilege";
    }


    @GetMapping("/addUser")
    private String addForm(Model model){
        model.addAttribute("user",new UserReqDto());
        return "admin/user/addUser";
    }

    @PostMapping(path = "/save")
    private String saveUser(UserReqDto user){
        userService.saveUser(user);
        return"redirect:/admin/utilisateur/index";
    }


    @PostMapping(path = "/addRoleToUser")
    public String addRoleToUser(RoleToUserReqDto rtu, RedirectAttributes ra){
        rtsService.addRoleToUser(rtu);
        ra.addAttribute("userId", rtu.getUserId());
        return "redirect:/admin/utilisateur/rolePrivilege";
    }

    @PostMapping(path = "/addPrivilegeToUser")
    public String addPrivilegeToUser(PrivilegeToUserReqDto ptu, RedirectAttributes ra){
        ptuService.addPrivilegeToUser(ptu);
        ra.addAttribute("userId", ptu.getUserId());
        return "redirect:/admin/utilisateur/rolePrivilege";
    }

    @GetMapping("/revokePrivilegeToUser")
    public String revokePrivilegeToUser(@Param("userId") Long userId, @Param("prvId") Long prvId, @Param("strId")Long strId, RedirectAttributes ra)
    {
        ptuService.revokePrivilegeToUser(userId, prvId, strId);
        ra.addAttribute("userId", userId);
        return "redirect:/admin/utilisateur/rolePrivilege";
    }

    //Affiche profil
    @GetMapping("/profil")
    public String indexProfile(){
        return "admin/user/profil";
    }

    //Affiche profil
    @GetMapping("/habilitations")
    public String indexHabilitation(Model model, @RequestParam(name = "userId") Long userId){
        List<RoleToUserInfo> userRoles=rtuRepo.getNoneAssignationForUser(userId);
        model.addAttribute("userRoles",userRoles);
        return "/";
    }



}
