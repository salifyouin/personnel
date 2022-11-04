package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.dto.TreeviewDto;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.service.implementation.StructureService;
import org.springframework.data.domain.Page;

import java.net.UnknownHostException;
import java.util.List;

public interface StructureIservice
{
    void saveStructure(StructureReqDto structureReqDto) throws UnknownHostException;
    String generateStrCode(String parentStrCode, String strType, Long strId);
    Page<StructureResDto> getAllPagesStructures(int page, int size);
    Page<StructureResDto> searchPageStructures(String critere,int page,int size);
    List<Structure> getAllSousStructure(Long strId);
    Structure loadChildrenTree(Structure structure);

    List<TreeviewDto> getTreeviewDtos(Long strId);

    List<TreeviewDto> getTreeviewRoot();

    List<Structure> getStructureByIdNul();
    List<Type> getTypesStructures();

    int getInverseLeve(Structure str);
}
