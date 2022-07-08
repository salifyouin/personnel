package ci.dgmp.personnel.security.model.projection;

import java.time.LocalDateTime;

public interface AppUserInfo {
    Long getUserId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    boolean isUserActive();

    String getUserEmail();

    String getUserLogin();

    String getUserNom();

    String getUserPrenom();

    String getUserTelephone();
}
