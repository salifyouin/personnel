package ci.dgmp.personnel.historisation.dto.mapper;

import ci.dgmp.personnel.historisation.entities.HistoData;
import ci.dgmp.personnel.historisation.entities.HistoStructure;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.security.model.entities.AppUser;
import org.hibernate.annotations.CreationTimestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface HistoStructureMapper
{
    @Mapping(target = "tutelleDirecteId", source = "structure.tutelleDirecte.strId")
    @Mapping(target = "strTypId", source = "structure.type.typId")
    HistoStructure mapToHistoStructure(Structure structure, HistoData histoData);

    @Mapping(target = "agtId", source = "user.agent.agtId")
    @Mapping(target = "agtNom", source = "user.agent.agtNom")
    @Mapping(target = "agtPrenom", source = "user.agent.agtPrenom")
    HistoData mapToHistoData(AppUser user, String action);
}
