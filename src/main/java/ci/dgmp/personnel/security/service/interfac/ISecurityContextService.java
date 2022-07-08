package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.projection.RoleToUserInfo;

import java.util.List;

public interface ISecurityContextService {
    String getAuthUsername();

    AppUser getAuthUser();

    List<RoleToUserInfo> getNoneRevokedAssForUser();

    boolean hasAuthority(String auth);

    boolean hasAuthority(String... auths);

    List<String> getUserAuthorities();

    void refreshSecurityContext();
}
