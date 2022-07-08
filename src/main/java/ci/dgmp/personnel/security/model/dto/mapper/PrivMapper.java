package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.security.model.dto.request.PrivilegeReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrivMapper {
    Privilege mapToPrivilege(PrivilegeReqDto dto);
    PrivilegeResDto mapToPrivilegeRes(Privilege privilege);
}
