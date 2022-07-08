package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.Structure;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StructureMapper {
    public StructureResDto mapToAgentResponse(Structure structure) {
        StructureResDto structureResDto = new StructureResDto();
        BeanUtils.copyProperties(structure, structureResDto);
        structureResDto.setStrtypId(structure.getType().getTypId());
        structureResDto.setStrStrId(structure.getTutelleDirecte().getStrId());
        return structureResDto;
    }

    public Structure mapToStructure(StructureReqDto structureReqDto) {
        Structure structure = new Structure();
        BeanUtils.copyProperties(structureReqDto, structureReqDto);
        //structure.setStructure(structureReqDto.getStrStrId());
//        agent.setFonction(agentReqDto.getAgtFonId()==null ? null:new Type(agentReqDto.getAgtFonId()));
//        agent.setStructure(agentReqDto.getAgtStrId()==null ? null : new Structure(agentReqDto.getAgtStrId()));
//        agent.setTypeAgt(agentReqDto.getAgtTypId()==null ? null : new Type(agentReqDto.getAgtTypId()));
//        agent.setGrade(agentReqDto.getAgtGradeId()==null ? null : new Type(agentReqDto.getAgtGradeId()));
        return structure;
    }
}
