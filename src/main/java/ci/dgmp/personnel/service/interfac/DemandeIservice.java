package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.dto.DemandeReqDto;

public interface DemandeIservice {
    void saveDemande(DemandeReqDto demandeReqDto);

    void saveAutorisationAbs(DemandeReqDto demandeReqDto);
}
