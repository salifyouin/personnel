package ci.dgmp.personnel.service.validator;

import ci.dgmp.personnel.model.dao.DemandeRepository;
import ci.dgmp.personnel.model.dto.TraitementReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraitementValidator {
    private final DemandeRepository demandeRepository;
    public void validateTraitement(TraitementReqDto traitement) {
       //Ici
    }
}
