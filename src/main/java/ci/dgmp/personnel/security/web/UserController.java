package ci.dgmp.personnel.security.web;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.projection.StructureInfo;
import ci.dgmp.personnel.security.model.dao.*;
import ci.dgmp.personnel.security.model.dto.request.ChangePasswordDto;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.*;
import ci.dgmp.personnel.security.model.projection.*;
import ci.dgmp.personnel.security.service.constant.ImgConstants;
import ci.dgmp.personnel.security.service.interfac.*;
import ci.dgmp.personnel.service.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private final ISecurityContextService scs;
    private final ImageIservice imageService;
    private final UserImageRepository imgRepo;

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/index")
    public String index(Model model) {
        List<AppUserInfo> users=userService.getAllUsers();
        model.addAttribute("users",users);
        return "admin/user/index";
    }

    @PreAuthorize("isAuthenticated()")
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
    public String addForm(Model model){
        model.addAttribute("user",new UserReqDto());
        return "admin/user/addUser";
    }

    @PostMapping(path = "/save")
    public String saveUser(@Valid UserReqDto user, BindingResult br, Model model)
    {
        if(br.hasErrors())
        {
            br.getGlobalErrors().forEach(err->model.addAttribute(err.getDefaultMessage().split(":")[0], err.getDefaultMessage().split(":")[1]));
            br.getFieldErrors().forEach(err-> model.addAttribute(err.getField(), err.getDefaultMessage()));
            return addForm(model);
        }
        userService.saveUser(user);
        return"redirect:/admin/utilisateur/index";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/addRoleToUser")
    public String addRoleToUser(RoleToUserReqDto rtu, RedirectAttributes ra){
        rtsService.addRoleToUser(rtu);
        ra.addAttribute("userId", rtu.getUserId());
        return "redirect:/admin/utilisateur/rolePrivilege";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/addPrivilegeToUser")
    public String addPrivilegeToUser(PrivilegeToUserReqDto ptu, RedirectAttributes ra){
        ptuService.addPrivilegeToUser(ptu);
        ra.addAttribute("userId", ptu.getUserId());
        return "redirect:/admin/utilisateur/rolePrivilege";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/revokePrivilegeToUser")
    public String revokePrivilegeToUser(@Param("userId") Long userId, @Param("prvId") Long prvId, @Param("strId")Long strId, RedirectAttributes ra)
    {
        ptuService.revokePrivilegeToUser(userId, prvId, strId);
        ra.addAttribute("userId", userId);
        return "redirect:/admin/utilisateur/rolePrivilege";
    }

    //Affiche profil
    @GetMapping("/profil") @PreAuthorize("isAuthenticated()")
    public String indexProfile(Model model){
        //Infos de l'utilisateur connecte
        AppUser user = scs.getAuthUser();
        user.setUserPassword("");
        model.addAttribute("user",user);
        model.addAttribute("image",new UserImage());
        model.addAttribute("userRoleAss",rtuRepo.getActiveAssignationForUser(user.getUserId()));
        return "admin/user/profil";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/changeRole")
    public String changeRole(@Param("userId") Long userId, @Param("roleId") Long roleId, @Param("strId")Long strId)
    {
        rtsService.changeRoleForUser(userId, roleId, strId);
        return "redirect:/";
    }

    //Modifier le profil
    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/updateProfil")
    public String updateProfile(AppUser user)
    {
        userService.updateUserProfile(user);
        return "redirect:/admin/utilisateur/profil";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/changePassword")
    public String changePassword(Model model){
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "admin/user/changePassword";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/changePassword")
    public String changePassword( Model model,
                                 HttpServletRequest request, @Valid ChangePasswordDto dto, BindingResult br) throws ServletException {
        if(br.hasErrors())
        {
            br.getGlobalErrors().forEach(err->model.addAttribute(err.getDefaultMessage().split(":")[0], err.getDefaultMessage().split(":")[1]));
            br.getFieldErrors().forEach(err-> model.addAttribute(err.getField(), err.getDefaultMessage()));
            return this.changePassword(model);
        }
        userService.changePassWord(dto);
        request.logout();
        return "redirect:/login";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/saveImage")
    public String saveImage(Model model,UserImage image){
        //model.addAttribute("image",image);
        imageService.saveImage(image);
        //request.logout();
        return "redirect:/admin/utilisateur/profil";
    }
//Affichage de l'image
    @GetMapping(path = "/photo/{userId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] displayPhoto(@PathVariable long userId)
    {
        String chemin  = imgRepo.findImgPath(userId);
        return imageService.download(chemin == null ? ImgConstants.defaultUserImg : chemin);
    }


}
