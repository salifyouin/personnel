package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.DemandeRepository;
import ci.dgmp.personnel.model.dto.DemandeReqDto;
import ci.dgmp.personnel.model.dto.mapper.IDemandeMapper;
import ci.dgmp.personnel.model.entities.Demande;
import ci.dgmp.personnel.service.interfac.DemandeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DemandeService implements DemandeIservice {
    private final DemandeRepository demandeRepository;
    private final IDemandeMapper demandeMapper;

    @Override
    public void saveDemande(DemandeReqDto demandeReqDto) {

    }

    @Override
    public void saveAutorisationAbs(DemandeReqDto demandeReqDto) {
        demandeReqDto = DemandeReqDto.builder()
                .demDateSaisie(LocalDateTime.now())
                .demNbrJour(10)
                .demDateFin(LocalDate.of(2022, 07, 10).plusDays(10))
                //.demAgtId()
                // Demande demande=demandeMapper.mapToDemandeAuth(demandeReqDto);
                .build();
        Demande demande = demandeMapper.mapToDemandeAuth(demandeReqDto);
        demandeRepository.save(demande);
    }

}
