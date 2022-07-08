package ci.dgmp.personnel.security.model.projection;

import ci.dgmp.personnel.model.entities.Structure;

import java.time.LocalDate;

public interface RoleToUserInfo {
    Long getAssId();

    boolean isAssActive();

    LocalDate getAssDateDebut();

    LocalDate getAssDateFin();

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
}
