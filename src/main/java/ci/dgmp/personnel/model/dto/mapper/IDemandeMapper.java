package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.DemandeReqDto;
import ci.dgmp.personnel.model.dto.DemandeResDto;
import ci.dgmp.personnel.model.entities.Demande;
import ci.dgmp.personnel.model.entities.DemandeAutorisation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface IDemandeMapper
{
    @Mapping(target = "agent.agtId", source = "dto.demAgtId")
    DemandeAutorisation mapToDemandeAuth(DemandeReqDto dto);

    DemandeAutorisation mapTodemadeResp(Demande demande);
}
