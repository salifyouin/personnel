package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.TypeRepository;
import ci.dgmp.personnel.model.dto.TypeReqDto;
import ci.dgmp.personnel.model.dto.mapper.TypeMapper;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.service.interfac.TypeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService implements TypeIservice {
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;
    @Override
    public void saveType(TypeReqDto typeReqDto) {
        Type type = new Type();
        type=typeMapper.mapToType(typeReqDto);
        type= typeRepository.save(type);
    }

    @Override
    public Page<Type> getAllPagesType(int page, int size) {
        List<Type> pagestypeParents= typeRepository.findByType_TypIdIsNullOrderByTypIdDesc(PageRequest.of(page,size));
        return new PageImpl<>(pagestypeParents,PageRequest.of(page,size),typeRepository.count());
    }

    @Override
    public Page<Type> getDetailsByParentId(Long typTypId, int page, int size) {
        return null;
    }

//    @Override
//    public Page<Type> getDetailsByParentId(Long typTypId,int page, int size) {
//        List<Type>pageDetailByParentId=typeRepository.findByType_TypIdOrderByTypIdDesc(typTypId,PageRequest.of(page,size));
//        return new PageImpl<>(pageDetailByParentId,PageRequest.of(page,size),typeRepository.count());
//    }


}
