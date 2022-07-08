package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;

public interface PrivilegeToUserIservice {
    void addPrivilegeToUser(PrivilegeToUserReqDto ptuRepo);
    void revokePrivilegeToUser(PrivilegeToUserReqDto ptu);

    void revokePrivilegeToUser(Long userId, Long prvId, Long strId);
}
