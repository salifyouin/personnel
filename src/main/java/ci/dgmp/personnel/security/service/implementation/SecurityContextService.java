package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.RoleToUserRepository;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.projection.RoleToUserInfo;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("scs")
@RequiredArgsConstructor
public class SecurityContextService implements ISecurityContextService
{
    private final RoleToUserRepository rtuRepo;
    private final IAuthorityService authorityService;
    private final UserRepository userRepo;

    @Override
    public String getAuthUsername()
    {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)
            return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((AppUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    @Override
    public AppUser getAuthUser()
    {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)
        {
            return new AppUser();
        }
        AppUser user = ((AppUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        user.setUserPassword("");
        return user;
    }
    @Override
    public List<RoleToUserInfo> getNoneRevokedAssForUser()
    {
        return rtuRepo.getNoneRevokedAssForUser(getAuthUser().getUserId());
    }

    @Override
    public boolean hasAuthority(String auth)
    {
        return this.getUserAuthorities().stream().anyMatch(a->a.equals(auth));
    }

    @Override
    public boolean hasAuthority(String... auths)
    {
        return this.getUserAuthorities().stream().anyMatch(auth0->Arrays.stream(auths).anyMatch(auth1->auth0.equals(auth1)));
    }

    @Override
    public List<String> getUserAuthorities()
    {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(auth->auth.getAuthority()).collect(Collectors.toList());
    }

    @Override
    public void refreshSecurityContext()
    {
        AppUser user = userRepo.findById(this.getAuthUser().getUserId()).get();
        Collection<? extends GrantedAuthority> auths = authorityService.getUserAuthorities(user.getUserId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(new AppUserDetails(user, auths), null, auths);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
