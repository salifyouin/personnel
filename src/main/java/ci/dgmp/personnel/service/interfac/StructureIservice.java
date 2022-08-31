package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StructureIservice
{
    void saveStructure(StructureReqDto structureReqDto);
    String generateStrCode(String parentStrCode, String strType, Long strId);
    Page<StructureResDto> getAllPagesStructures(int page, int size);
    Page<StructureResDto> searchPageStructures(String critere,int page,int size);
    List<Structure> getAllSousStructure(Long strId);
    Structure loadChildrenTree(Structure structure);
    List<Structure> getStructureByIdNul();
    List<Type> getTypesStructures();
}
