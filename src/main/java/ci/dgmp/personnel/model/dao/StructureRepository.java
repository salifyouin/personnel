package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.model.projection.StructureInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StructureRepository extends JpaRepository<Structure, Long> {
    @Query("select new ci.dgmp.personnel.model.dto.StructureResDto(s) from Structure s order by s.strId DESC")
    List<StructureResDto> getAllPagesStructures(Pageable pageable);

    @Query("select new ci.dgmp.personnel.model.dto.StructureResDto(s) " +
            "from Structure s left join s.tutelleDirecte left join s.type" +
            " where s.tutelleDirecte.tutelleDirecte.strId is not null" +
            " and upper(concat(coalesce(s.strLibelle,'')," +
            "coalesce(s.strSigle,'') ) ) " +
            "like upper(concat('%', :critere, '%')) " +
            "order by s.strId DESC")
    List<StructureResDto> searchPageStructures(@Param("critere") String critere, Pageable pageable);

    @Query("select new ci.dgmp.personnel.model.dto.StructureResDto(s) from Structure s where s.tutelleDirecte.strId is null order by s.strId DESC")
    List<Structure> getStrParents();

    @Query("select s from Structure s where s.tutelleDirecte.strId is not null order by s.strLibelle")
    List<StructureInfo> getAllStructureFille();

    @Query("select s from Structure s where s.type.typLevel <( select t.typLevel from Type t where t.typId = ?1)")
    List<Structure> getStrMereByTypeId(Long typId);

    @Query("select s from Structure s where s.type.typLevel < ?1")
    List<Structure> getStrMereByTypeLevel(Long level);










    //Afficher la liste des structure dont le strId est null.
    List<Structure> findStructureByTutelleDirecteIsNull();

    //Afficher la liste des structure dont le strId est egale a strId
    //List<Structure> findStructureByStructureStrId(Long strId);
    @Query("select s from Structure s where s.tutelleDirecte.strId = ?1")
    List<Structure> findStructureByTutelleDirecte(Long strId);

    List<Structure> findByStrLevel(long strLevel);


    //List<Type> findTypeByType_TypId(long id);
}
