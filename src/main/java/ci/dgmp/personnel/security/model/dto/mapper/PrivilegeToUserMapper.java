package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeToRoleReqDto;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToUserResDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface PrivilegeToUserMapper
{
    @Mapping(target = "privilege.privilegeId", source = "dto.privilegeId")
    @Mapping(target = "appUser.userId", source = "dto.userId")
    @Mapping(target = "structure.strId", source = "dto.assStrId")
    PrivilegeToUser mapToPrivilegeToUser(PrivilegeToUserReqDto dto);

//    @Mapping(target="privilegeId", source = "privilegeToUser.privilege.privilegeId")
//    @Mapping(target="privilegeCode", source = "privilegeToUser.privilege.privilegeCode")
//    @Mapping(target="privilegeName", source = "privilegeToUser.privilege.privilegeName")
//    @Mapping(target="userId", source = "privilegeToUser.appUser.userId")
//    @Mapping(target="userNom", source = "privilegeToUser.appUser.userNom")
//    @Mapping(target="userTelephone", source = "privilegeToUser.appUser.userTelephone")
//    @Mapping(target="assStrId", source = "privilegeToUser.structure.strId")
//    @Mapping(target="strSigle", source = "privilegeToUser.structure.strSigle")
//    @Mapping(target="strLibelle", source = "privilegeToUser.structure.strLibelle")
//    @Mapping(target="userActive", source = "privilegeToUser.appUser.userActive")
//    @Mapping(target="agtId", source = "privilegeToUser.appUser.agent.agtId")
//    @Mapping(target="agtMatricule", source = "privilegeToUser.appUser.agent.agtMatricule")
//    PrivilegeToUserResDto mapToPrivilegeToUserResDto(PrivilegeToUser privilegeToUser);
}
