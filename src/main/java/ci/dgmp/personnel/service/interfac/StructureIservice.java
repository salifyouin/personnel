package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.Structure;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StructureIservice
{
    public void savePere(StructureReqDto structureReqDto);
    Page<StructureResDto> getAllPagesStructures(int page, int size);
    Page<StructureResDto> searchPageStructures(String critere,int page,int size);
    List<Structure> getAllSousStructure(Long strId);
    Structure loadChildrenTree(Structure structure);
    List<Structure> getStructureByIdNul();
}
