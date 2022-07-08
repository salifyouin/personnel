package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeToRoleReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.projection.PrivilegeToRoleInfo;

import java.util.List;

public interface PrivilegeToRoleIservice {
    void addPrivillegeToRole(PrivilegeToRoleReqDto ptrRepo);
    //void addPrivillegeToRole(PrivilegeToRole ptr, Role role, Privilege privilege);
    List<PrivilegeToRoleInfo> getPrivillegeByRole(Long roleId);
    //Liste des assignations actives
    List<PrivilegeToRoleInfo> getActivePrivilgeToRoleAss(Long roleId);

    void desablePrivilege(PrivilegeToRole ptr);
    //Revoquer un privilege a un role
    void revokePrivilegeToRole(Long roleId, Long prvId);
}
