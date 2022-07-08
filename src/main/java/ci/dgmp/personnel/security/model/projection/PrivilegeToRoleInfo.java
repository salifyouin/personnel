package ci.dgmp.personnel.security.model.projection;

import java.time.LocalDate;

public interface PrivilegeToRoleInfo {
    Long getAssId();

    boolean isAssActive();

    LocalDate getAssDateDebut();

    LocalDate getAssDateFin();

    PrivilegeInfo getPrivilege();

    RoleInfo getRole();

    StructureInfo getStructure();

    interface PrivilegeInfo {
        Long getPrivilegeId();

        String getPrivilegeName();
    }

    interface RoleInfo {
        long getRoleId();

        String getRoleName();
    }

    interface StructureInfo {
        Long getStrId();

        String getStrLibelle();

        String getStrSigle();
    }
}
