package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.DemandeRepository;
//import ci.dgmp.personnel.model.dto.DemandeReqDto;
//import ci.dgmp.personnel.model.dto.mapper.IDemandeMapper;
import ci.dgmp.personnel.model.entities.Demande;
//import ci.dgmp.personnel.service.interfac.DemandeIservice;
import ci.dgmp.personnel.security.service.implementation.SecurityContextService;
import ci.dgmp.personnel.security.service.interfac.IAuthorityService;
import ci.dgmp.personnel.service.interfac.DemandeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DemandeService implements DemandeIservice {
    private final SecurityContextService scs;
    private final IAuthorityService authService;

    @Override
    public Long getAuthAgent() {
        Long agtId;
        return agtId = authService.getActiveRoleAssForUser(scs.getAuthUser().getUserId()).getAppUser().getAgent().getAgtId();
    }
}
