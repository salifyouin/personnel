package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.dto.request.RoleReqDto;
import ci.dgmp.personnel.security.model.dto.responses.RoleResDto;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.projection.RoleInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleIservice {
    Page<RoleResDto> getAllRoles(int page, int size);
    Page<RoleResDto> getAllRoles(String critere, int page, int size);
    List<RoleInfo> getAllRoles();
    void saveRole(RoleReqDto roleReqDto);
    void saveRole(Role role);
    void updateRole(RoleReqDto roleReqDto);
    void deleteRole(Long id);

}
