package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dao.RoleRepository;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PrivilegeRepository privilegeRepo;
    private final IAuthorityService authorityService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Recuperation de l'utilisateur AppUser dans la base a partir du userName(userLogin)
        AppUser user=userRepo.findByUserLogin(username).orElseThrow(()-> new UsernameNotFoundException("Utilisateur introuvable"));
        //Convertir le AppUser a un objet de type AppUserDetails

   //Recuperation des privileges et roles du User
        Collection<? extends GrantedAuthority> authorities = authorityService.getUserAuthorities(user.getUserId());
        return new AppUserDetails(user, authorities);
    }
}



