package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.dto.AgentResDto;
//import ci.dgmp.personnel.model.dto.DemandeResDto;
import ci.dgmp.personnel.model.entities.Demande;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

//    @Query("select new ci.dgmp.personnel.model.dto.DemandeResDto(d) from Demande d order by d.demId DESC")
//    List<DemandeResDto> getAllDemandes(@Param("critere") String critere, Pageable pageable);

}
