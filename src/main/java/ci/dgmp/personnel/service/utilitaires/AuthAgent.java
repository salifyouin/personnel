package ci.dgmp.personnel.service.utilitaires;

import ci.dgmp.personnel.security.service.implementation.SecurityContextService;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthAgent {
    private final SecurityContextService scs;
    private final IAuthorityService authService;

    public Long getAuthAgent() {
        Long agtId;
        return agtId = authService.getActiveRoleAssForUser(scs.getAuthUser().getUserId()).getAppUser().getAgent().getAgtId();
    }
}
