package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.dto.mapper.StructureMapper;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StructureService implements StructureIservice {
    private final StructureRepository structureRepository;
    private final StructureMapper structureMapper;

    //Enregistrement d'une structure père
    @Override
    public void savePere(StructureReqDto structureReqDto) {
        Structure structure=structureMapper.mapToStructure(structureReqDto);
        structure=structureRepository.save(structure);
    }

    //Affiche tout sans filtre
    @Override
    public Page<StructureResDto> getAllPagesStructures(int page, int size) {
        List<StructureResDto> getAllPagesStructures=structureRepository.getAllPagesStructures(PageRequest.of(page,size));
        return new PageImpl<>(getAllPagesStructures,PageRequest.of(page,size),structureRepository.count());
    }

    //Recherche et pagination
    @Override
    public Page<StructureResDto> searchPageStructures(String critere, int page, int size) {
        List<StructureResDto> searchPageStructures=structureRepository.searchPageStructures(critere,PageRequest.of(page,size));
        return new PageImpl<>(searchPageStructures,PageRequest.of(page,size),structureRepository.count());
    }

    public Stream<Structure> getAllSousStructureAsStream(Long strId)
    {
        return Stream.concat(
                Stream.of(structureRepository.findById(strId).orElse(null)),
                structureRepository.findStructureByTutelleDirecte(strId).stream().flatMap(fils-> getAllSousStructureAsStream(fils.getStrId()))).filter(Objects::nonNull);
    }

    @Override
    public List<Structure> getAllSousStructure(Long strId)
    {
        return this.getAllSousStructureAsStream(strId).collect(Collectors.toList());
    }



    @Override
    public Structure loadChildrenTree(Structure structure)
    {
        //Structure parent = structureRepository.findById(strId).orElse(null);
        structure.setStructuresSousTutelle(structureRepository.findStructureByTutelleDirecte(structure.getStrId()));
        structure.getStructuresSousTutelle().forEach(child->loadChildrenTree(child));

        return structure;
    }

    @Override
    public List<Structure> getStructureByIdNul() {
        return structureRepository.findStructureByTutelleDirecteIsNull();
    }


}
