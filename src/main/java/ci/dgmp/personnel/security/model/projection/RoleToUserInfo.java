package ci.dgmp.personnel.security.model.projection;

import ci.dgmp.personnel.model.entities.Structure;

import java.time.LocalDate;

public interface RoleToUserInfo {
    Long getAssId();

    Long getAssActive();

    LocalDate getAssDateDebut();

    LocalDate getAssDateFin();
    UserInfo getAppUser();
    RoleInfo getRole();

    StructureInfo getStructure();

    interface RoleInfo {
        long getRoleId();

        boolean isRoleActif();

        String getRoleCode();

        String getRoleName();
    }

    interface StructureInfo {
        Long getStrId();

        String getStrLibelle();

        String getStrSigle();

        Structure getTutelleDirecte();
    }

    interface UserInfo
    {
        Long getUserId();
        Long getUserLogin();
        Long getUserEmail();
        boolean isUserActive();
    }
}
