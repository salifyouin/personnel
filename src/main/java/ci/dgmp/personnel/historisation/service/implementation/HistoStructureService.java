package ci.dgmp.personnel.historisation.service.implementation;

import ci.dgmp.personnel.historisation.dao.HistoStructureRepository;
import ci.dgmp.personnel.historisation.dto.mapper.HistoStructureMapper;
import ci.dgmp.personnel.historisation.entities.HistoStructure;
import ci.dgmp.personnel.historisation.service.interfac.HistoStructureIservice;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.security.service.implementation.SecurityContextService;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import ci.dgmp.personnel.service.utilitaires.AuthAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoStructureService implements HistoStructureIservice {
    private final HistoStructureRepository hisRepo;
    private final ISecurityContextService scs;
    private final HistoStructureMapper histoMapper;
    @Override
    public void saveHistoStructure(Structure structure, String action) {
        HistoStructure histo= histoMapper.mapToHistoStructure(structure, scs.getHistoData(action));
        hisRepo.save(histo);
    }
}
