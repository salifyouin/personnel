package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.*;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("authService")
@RequiredArgsConstructor
public class AuthorityService implements IAuthorityService
{
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PrivilegeToRoleRepository ptrRepo;
    private final PrivilegeToUserRepository ptuRepo;
    private final RoleToUserRepository rtuRepo;
    private final PrivilegeRepository privilegeRepo;
    @Override
    // combobox
    public boolean roleHasPrivilege(Long roleId, Long privilegeId)
    {
        return ptrRepo.roleHasPrivilege(roleId, privilegeId);
    }
    //LIste des habilitations(priviles et roles) d'un utilisateur
    @Override
    public Collection<? extends GrantedAuthority> getUserAuthorities(Long userId)
    {
        AppUser user = userRepo.findById(userId).orElse(null);//Recuperer l'utilisateur a partir de son id
        if(user==null) return new ArrayList<>();
        Role role = roleRepo.getActiveRoleForUser(user.getUserId());
        role = role!=null ? role : roleRepo.findByRoleCode("USER");
        List<Privilege> privileges = Stream.concat(
                privilegeRepo.getActivePrivilegesForRole(role.getRoleId()).stream(),
                privilegeRepo.getActivePrivilegesForUser(user.getUserId()).stream()).collect(Collectors.toList());
        Collection<? extends GrantedAuthority> authorities = Stream.concat(Stream.of(role.getRoleName()),
                privileges.stream().map(prv->prv.getPrivilegeCode())).distinct().map(auth->new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public RoleToUser getActiveRoleAssForUser(Long userId)
    {
        return rtuRepo.getActiveAssignationForUser(userId);
    }

    @Override
    //Liste des des assignations (roles) d'un utilisateur dans une structure
    //Combox pour ramener la liste des roles non assignés a un utilisateur dans une structure
    public boolean userHasRole(Long userId, Long roleId, Long strId)
    {
        return rtuRepo.userHasRole(userId, roleId, strId);
    }

    @Override
    //Liste des des assignations (privileges) d'un utilisateur dans une structure
    public boolean userHasPrivilege(Long userId, Long privilegeId, Long strId)
    {
        if(ptuRepo.userHasDirectPrivilege(userId, privilegeId, strId)) return true;//Recuperer les privileges directe d'un utilisateur
        if(ptuRepo.isPrivilegeRevokedForUser(userId, privilegeId, strId)) return false;
        RoleToUser activeAss = rtuRepo.getActiveAssignationForUser(userId);////Recuperer les roles(assignations) active d'un utilisateu
        if(activeAss == null) return false;
        if(activeAss.getStructure() == null) return false;
        if(!activeAss.getStructure().getStrId().equals(strId)) return false;
        return ptrRepo.roleHasPrivilege(activeAss.getRole().getRoleId(), privilegeId);
    }
}
