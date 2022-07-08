package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.TraitementReqDto;
import ci.dgmp.personnel.model.dto.TraitementResDto;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Traitement;
import ci.dgmp.personnel.model.entities.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TraitementMapper {
    public TraitementResDto mapToTraitementResponse(Traitement traitement) {
        TraitementResDto traitementResDto = new TraitementResDto();
        BeanUtils.copyProperties(traitement, traitementResDto);
        traitementResDto.setTraiDemId(traitement.getTraitId());
        traitementResDto.setTraiAgtId(traitement.getTraitId());
        traitementResDto.setTraiAgtNom(traitement.getAgent().getAgtNom());
        traitementResDto.setTraiAgtNom(traitement.getAgent().getAgtPrenom());
        return traitementResDto;
    }

    public Traitement mapToType(TraitementReqDto traitementReqDto) {
        Traitement traitement = new Traitement();
        BeanUtils.copyProperties(traitementReqDto, traitement);
        traitement.setType(new Type(traitement.getType().getTypId()));
        traitement.setAgent(new Agent(traitement.getAgent().getAgtId()));
        return traitement;
    }
}
