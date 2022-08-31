package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Mapper(componentModel = "spring")
public interface StructureMapper {

    @Mapping(target = "tutelleDirecte", expression = "java(dto.getStrStrId()==null ? null : new ci.dgmp.personnel.model.entities.Structure(dto.getStrStrId()))")
    @Mapping(target = "type", expression = "java(dto.getStrtypId()==null ? null : new ci.dgmp.personnel.model.entities.Type(dto.getStrtypId()))")
    public Structure mapToStructure(StructureReqDto dto);
}
