package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.entities.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("scs")
public class SecurityContextService
{
    public String getAuthUsername()
    {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)
            return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((AppUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public boolean hasAuthority(String auth)
    {
        return this.getUserAuthorities().stream().anyMatch(a->a.equals(auth));
    }

    public boolean hasAuthority(String ...auths)
    {
        return this.getUserAuthorities().stream().anyMatch(auth0->Arrays.stream(auths).anyMatch(auth1->auth0.equals(auth1)));
    }

    public List<String> getUserAuthorities()
    {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(auth->auth.getAuthority()).collect(Collectors.toList());
    }
}
