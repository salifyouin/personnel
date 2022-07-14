package ci.dgmp.personnel.model.dao;
import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.entities.Agent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    @Query("select a from Agent a where a.structure.strId = ?1")
    List<Agent> findByStrId(Long strId);

    @Query("select a from Agent a where a.agtId = :aLong")
    @Override
    Optional<Agent> findById(@Param("aLong") Long aLong);

    @Query("select new ci.dgmp.personnel.model.dto.AgentResDto(a) " +
            "from Agent a left join a.structure s " +
            "where upper(concat(coalesce(a.agtNom,''), " +
            "coalesce(a.agtPrenom,''), " +
            "coalesce(a.agtMatricule,''), " +
            "coalesce(a.agtTel,''), " +
            "coalesce(a.agtAdresse,''), " +
            "coalesce(a.grade.typLibelle,''), " +
            "coalesce(a.fonction.typLibelle,''), " +
            "coalesce(a.structure.strLibelle,'') )) " +
            "like upper(concat('%', :critere, '%')) " +
            "order by a.agtId DESC")
    List<AgentResDto> searchPageAgent(@Param("critere") String critere, Pageable pageable);

    @Query("select new ci.dgmp.personnel.model.dto.AgentResDto(a) from Agent a order by a.agtId DESC")
    List<AgentResDto> getAllPagesAgents(Pageable pageable);


    @Query("select  upper(concat(coalesce(a.agtNom,''), coalesce(a.agtPrenom,''), coalesce(a.structure.strLibelle,''))) from Agent a left join a.structure s where upper(concat(coalesce(a.agtNom,''), coalesce(a.agtPrenom,''), coalesce(a.structure.strLibelle,'') )) like upper(concat('%', :critere, '%'))")
    List<String> selectConcat(@Param("critere") String critere);

    @Query("select a from Agent a where a.structure.strId = ?1 order by a.agtId DESC")
    List<Agent> getAgents(Long strId);

    //Valider le username par programmation
    Agent findByAgtUserName(String agtUserName);

    //Valider le username avec anotation personalisee
    boolean existsByAgtUserName(String agtUserName);

    boolean existsByAgtAdresse(String agtAdresse);

    @Query("select (count(a) > 0) from Agent a where upper(a.agtMatricule) = upper(?1)")
    boolean existsByAgtMatricule(String agtMatricule);












}
