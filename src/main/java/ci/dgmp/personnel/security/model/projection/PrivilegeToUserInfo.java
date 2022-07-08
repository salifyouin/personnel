package ci.dgmp.personnel.security.model.projection;

import ci.dgmp.personnel.model.entities.Structure;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PrivilegeToUserInfo {
    Long getAssId();

    boolean isAssActive();

    LocalDate getAssDateDebut();

    LocalDate getAssDateFin();

    AppUserInfo getAppUser();

    PrivilegeInfo getPrivilege();

    StructureInfo getStructure();

    interface AppUserInfo {
        Long getUserId();
    }

    interface PrivilegeInfo {
        Long getPrivilegeId();

        boolean isPrivilegeActif();

        String getPrivilegeCode();

        LocalDateTime getPrivilegeCretatedAt();

        String getPrivilegeName();
    }

    interface StructureInfo {
        Long getStrId();

        String getStrCode();

        String getStrLibelle();

        String getStrSigle();

        Structure getTutelleDirecte();
    }
}
