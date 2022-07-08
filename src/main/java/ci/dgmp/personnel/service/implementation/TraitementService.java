package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.TraitementRepository;
import ci.dgmp.personnel.service.interfac.TraitementIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraitementService implements TraitementIService {
    private final TraitementRepository traitementRepository;
}
