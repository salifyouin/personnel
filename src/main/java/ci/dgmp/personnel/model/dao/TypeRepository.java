package ci.dgmp.personnel.model.dao;

import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.entities.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findByType_TypIdIsNullOrderByTypIdDesc(Pageable pageable);

    List<Type> findTypeByType_TypId(long id);


    List<Type> findByType_TypIdOrderByTypIdDesc(Long typId);

    @Query("select t from Type t where t.type.typId = (select t2.typId from Type t2 where t2.typCode = ?1)")
    List<Type> getTypesFils(String typeCode);







    List<Type> findByType_TypIdIsNull();
    //List<Type> findTypeByType_TypId
}
