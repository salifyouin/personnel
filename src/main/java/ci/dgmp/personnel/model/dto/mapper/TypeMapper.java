package ci.dgmp.personnel.model.dto.mapper;

import ci.dgmp.personnel.model.dto.TypeReqDto;
import ci.dgmp.personnel.model.dto.TypeResDto;
import ci.dgmp.personnel.model.entities.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper {
    public TypeResDto mapToTypeResponse(Type type) {
        TypeResDto typeResDto = new TypeResDto();
        BeanUtils.copyProperties(type, typeResDto);
        typeResDto.setTypTypId(type.getType().getTypId());
        return typeResDto;
    }

    public Type mapToType(TypeReqDto typeReqDto) {
        Type type = new Type();
        BeanUtils.copyProperties(typeReqDto, type);
        type.setType(new Type(typeReqDto.getTypTypId()));
        return type;
    }
}
