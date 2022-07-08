package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.security.model.dto.request.RoleReqDto;
import ci.dgmp.personnel.security.model.dto.responses.RoleResDto;
import ci.dgmp.personnel.security.model.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role mapToRole(RoleReqDto dto);
    RoleResDto mapToRoleResDto(Role role);
}
