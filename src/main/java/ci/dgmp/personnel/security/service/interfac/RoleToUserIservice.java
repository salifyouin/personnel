package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.projection.RoleToUserInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleToUserIservice {
    List<RoleToUserInfo> getRoleByUser(Long userId);
    //Assignation non revoquées de l'utilisation
    List<RoleToUserInfo> getNoneAssignationForUser(Long userId);
    void addRoleToUser(RoleToUserReqDto rtuRepo);


    void changeRoleForUser(Long userId, Long roleId, Long strId);
}

