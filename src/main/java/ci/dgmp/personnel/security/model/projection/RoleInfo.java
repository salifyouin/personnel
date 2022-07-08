package ci.dgmp.personnel.security.model.projection;

import java.time.LocalDateTime;

public interface RoleInfo {
    long getRoleId();

    LocalDateTime getCreatedAt();

    boolean isRoleActif();

    String getRoleCode();

    String getRoleName();

    LocalDateTime getUpdatedAt();
}
