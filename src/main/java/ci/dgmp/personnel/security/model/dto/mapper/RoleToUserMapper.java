package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToUserResDto;
import ci.dgmp.personnel.security.model.dto.responses.RoleToUserResDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleToUserMapper
{
    @Mapping(target = "role.roleId", source = "dto.roleId")
    @Mapping(target = "appUser.userId", source = "dto.userId")
    @Mapping(target = "structure.strId", source = "dto.assStrId")
    RoleToUser mapToRoleToUser(RoleToUserReqDto dto);
}
