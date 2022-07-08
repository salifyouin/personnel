package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeToRoleReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrivilegeToRoleMapper {
    PrivilegeResDto mapToPrivilegeRespDto(Privilege privilege);

    @Mapping(target = "privilege.privilegeId", source = "dto.privilegeId")
    @Mapping(target = "role.roleId", source = "dto.roleId")
    @Mapping(target = "structure", expression = "java(dto.getAssStrId()==null? null : new ci.dgmp.personnel.model.entities.Structure(dto.getAssStrId()))")
    PrivilegeToRole mapToPrivilegeToRole(PrivilegeToRoleReqDto dto);

    @Mapping(target="privilegeId", source = "privilegeToRole.privilege.privilegeId")
    @Mapping(target="privilegeCode", source = "privilegeToRole.privilege.privilegeCode")
    @Mapping(target="privilegeName", source = "privilegeToRole.privilege.privilegeName")
    @Mapping(target="roleId", source = "privilegeToRole.role.roleId")
    @Mapping(target="roleCode", source = "privilegeToRole.role.roleCode")
    @Mapping(target="roleName", source = "privilegeToRole.role.roleName")

    @Mapping(target="assStrId", source = "privilegeToRole.structure.strId")
    @Mapping(target="strSigle", source = "privilegeToRole.structure.strSigle")
    @Mapping(target="strLibelle", source = "privilegeToRole.structure.strLibelle")
    PrivilegeToRoleResDto mapToPrivilegeToUserResDto(PrivilegeToRole privilegeToRole);
}
