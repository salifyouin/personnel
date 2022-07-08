package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.DemandeReqDto;
import ci.dgmp.personnel.model.dto.DemandeResDto;
import ci.dgmp.personnel.model.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DemandeMapper {
    public DemandeResDto mapToDemandeResponse(Demande demande) {
        DemandeResDto demandeResDto = new DemandeResDto();
        BeanUtils.copyProperties(demande, demandeResDto);
        demandeResDto.setDemAgtId(demande.getAgent().getAgtId());
        //demandeResDto.setDemTypId(demande.getType().getTypId());
        demandeResDto.setMatriculeAgent(demande.getAgent().getAgtMatricule());
        demandeResDto.setNomAgent(demande.getAgent().getAgtNom());
        demandeResDto.setPrenomAgent(demande.getAgent().getAgtPrenom());
        //demandeResDto.setTypeDemande(demande.getType().getTypLibelle());
        demandeResDto.setStructueAgent(demande.getAgent().getStructure().getStrLibelle());
        return demandeResDto;
    }

    public Demande mapToDemande(DemandeReqDto demandeReqDto) {
        Demande demande;
        switch (demandeReqDto.getDemType())
        {
            case "AUTH":
                demande = new DemandeAutorisation();
                break;
            default: demande = new DemandeAutorisation();
        }
        BeanUtils.copyProperties(demandeReqDto, demande);
        demande.setAgent(new Agent(demandeReqDto.getDemAgtId()));
        //demande.setType(new Type(demandeReqDto.getDemTypId()));
        return demande;
    }
}
