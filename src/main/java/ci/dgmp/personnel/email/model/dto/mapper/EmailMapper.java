package ci.dgmp.personnel.email.model.dto.mapper;

import ci.dgmp.personnel.email.model.dto.EmailDto;
import ci.dgmp.personnel.email.model.entities.Email;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.security.model.entities.AppUser;
import org.hibernate.tuple.component.ComponentMetamodel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Mapper(componentModel="spring")
public interface EmailMapper {
    Email mapToEmail(EmailDto emailDto);

    @Mapping(target = "username", source = "user.userLogin")
    @Mapping(target = "email", source = "user.userEmail")
    @Mapping(target = "sendingDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "seen", expression = "java(false)")
    @Mapping(target = "sent", expression = "java(false)")
    Email mapToEmailDto(AppUser user);
}
