package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.historisation.entities.HistoStructure;
import ci.dgmp.personnel.historisation.service.interfac.HistoStructureIservice;
import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dao.TypeRepository;
import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.dto.mapper.StructureMapper;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StructureService implements StructureIservice {
    private final StructureRepository strRepo;
    private final StructureMapper structureMapper;
    private final TypeRepository typeRepo;
    private final HistoStructureIservice histoService;

    //Enregistrement d'une structure père
    @Override @Transactional
    public void saveStructure(StructureReqDto structureReqDto)
    {
        Structure structure=structureMapper.mapToStructure(structureReqDto);

        structure= strRepo.save(structure);
        structure.setTutelleDirecte(strRepo.findById(structure.getTutelleDirecte().getStrId()).orElse(null));
        structure.setStrLevel(structure.getTutelleDirecte() == null ? 1 : structure.getTutelleDirecte().getStrLevel()+1);
        String strCode = this.generateStrCode(structure.getTutelleDirecte() == null ? null : structure.getTutelleDirecte().getStrCode(),
                typeRepo.findById(structure.getType().getTypId()).orElse(null).getTypCode(), structure.getStrId());
        structure.setStrCode(strCode);
        histoService.saveHistoStructure(structure, "CREATION");
    }

    @Override
    public String generateStrCode(String parentStrCode, String strType, Long strId)
    {
        String strCode = (parentStrCode== null ? "" : parentStrCode + "/") + strType + "-" + strId;
        return strCode;
    }

    //Affiche tout sans filtre
    @Override
    public Page<StructureResDto> getAllPagesStructures(int page, int size) {
        List<StructureResDto> getAllPagesStructures= strRepo.getAllPagesStructures(PageRequest.of(page,size));
        return new PageImpl<>(getAllPagesStructures,PageRequest.of(page,size), strRepo.count());
    }

    //Recherche et pagination
    @Override
    public Page<StructureResDto> searchPageStructures(String critere, int page, int size) {
        List<StructureResDto> searchPageStructures= strRepo.searchPageStructures(critere,PageRequest.of(page,size));
        return new PageImpl<>(searchPageStructures,PageRequest.of(page,size), strRepo.count());
    }

    public Stream<Structure> getAllSousStructureAsStream(Long strId)
    {
        return Stream.concat(
                Stream.of(strRepo.findById(strId).orElse(null)),
                strRepo.findStructureByTutelleDirecte(strId).stream().flatMap(fils-> getAllSousStructureAsStream(fils.getStrId()))).filter(Objects::nonNull);
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
        structure.setStructuresSousTutelle(strRepo.findStructureByTutelleDirecte(structure.getStrId()));
        structure.getStructuresSousTutelle().forEach(child->loadChildrenTree(child));

        return structure;
    }

    @Override
    public List<Structure> getStructureByIdNul() {
        return strRepo.findStructureByTutelleDirecteIsNull();
    }

    @Override
    public List<Type> getTypesStructures() {
        return typeRepo.getTypesFils("STR");
    }


}
