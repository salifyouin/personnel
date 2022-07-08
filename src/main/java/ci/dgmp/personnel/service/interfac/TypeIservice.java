package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.model.dto.TypeReqDto;
import ci.dgmp.personnel.model.entities.Type;
import org.springframework.data.domain.Page;

public interface TypeIservice {
    void saveType(TypeReqDto typeReqDto);
    Page<Type> getAllPagesType(int page, int size);

    //Afficher les deatils en passant l'Id des parent en parametre
    Page<Type> getDetailsByParentId(Long typTypId,int page,int size);


}
