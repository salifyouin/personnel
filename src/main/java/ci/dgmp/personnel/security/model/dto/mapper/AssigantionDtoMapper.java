package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.model.dto.DemandeReqDto;
import ci.dgmp.personnel.model.entities.Demande;
import ci.dgmp.personnel.model.entities.DemandeAutorisation;
import ci.dgmp.personnel.security.model.dto.request.AssignationReqDto;
import ci.dgmp.personnel.security.model.entities.Assignation;
import ci.dgmp.personnel.security.model.enums.AssType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

//@Mapper(componentModel = "spring")
//public abstract class AssigantionDtoMapper
//{
//    @Autowired protected RoleToUserMapper roleToUserMapper;
//    @Autowired protected PrivilegeToUserMapper privilegeToUserMapper;
//    @Autowired protected PrivilegeToRoleMapper privilegeToRoleMapper;
//    Assignation mapToAssignation(AssignationReqDto dto)
//    {
//        switch (dto.getType())
//        {
//            case "RoleToUser":
//                return roleToUserMapper.mapToPrivilegeToRole(dto.getType().);
//            case "PrivilegeToRole":
//            case "PrivilegeToUser":
//        }
//    }
//
//}


