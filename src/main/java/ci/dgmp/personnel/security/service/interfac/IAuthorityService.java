package ci.dgmp.personnel.security.service.interfac;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface IAuthorityService
{
    //Recuperer la liste des privileges dliés a un role
    boolean roleHasPrivilege(Long roleId, Long privilegeId);
    //LIste des habilitations(priviles et roles) d'un utilisateur
     Collection<? extends GrantedAuthority> getUserAuthorities(Long userId);
     //Liste des des assignations (roles) d'un utilisateur dans une structure
     boolean userHasRole(Long userId, Long roleId, Long strId);
    //Liste des des assignations (privileges) d'un utilisateur dans une structure
     boolean userHasPrivilege(Long userId, Long privilegeId, Long strId);
}
