package ci.dgmp.personnel.security.model.projection;

import java.time.LocalDateTime;

public interface PrivilegeInfo {
    Long getPrivilegeId();

    boolean isPrivilegeActif();

    String getPrivilegeCode();

    LocalDateTime getPrivilegeCretatedAt();

    String getPrivilegeName();

    LocalDateTime getPrivilegeUpdatedAt();
}
