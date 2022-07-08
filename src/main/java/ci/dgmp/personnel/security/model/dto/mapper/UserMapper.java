package ci.dgmp.personnel.security.model.dto.mapper;

import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "userActive", expression = "java(true)")
    AppUser mapToUser(UserReqDto userReqDto);

    @Mapping(target = "userNom", source = "dto.agtNom")
    @Mapping(target = "userPrenom", source = "dto.agtPrenom")
    @Mapping(target = "userLogin", source = "dto.agtUserName")
    @Mapping(target = "userPassword", source = "dto.agtPassword")
    @Mapping(target = "userTelephone", source = "dto.agtTel")
    @Mapping(target = "userEmail", source = "dto.agtAdresse")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "userActive", expression = "java(true)")
    AppUser mapToUser(AgentReqDto dto);
}
