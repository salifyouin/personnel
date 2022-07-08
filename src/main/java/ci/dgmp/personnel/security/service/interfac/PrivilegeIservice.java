package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PrivilegeIservice {
    void savePrivilege(PrivilegeReqDto privilegeReqDto);
    List<PrivilegeResDto> getAllPrivileges();
    Page<PrivilegeResDto> getAllPrivileges(int page, int size);
    Page<PrivilegeResDto> getAllPrivileges(String critere, int page, int size);
    void savePrivilege(Privilege privilege);

    List<Privilege> getUserPrivileges(Long userId, Long strId);
}
