package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.historisation.service.interfac.HistoStructureIservice;
import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dao.TypeRepository;
import ci.dgmp.personnel.model.dto.StructureReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.dto.TreeviewDto;
import ci.dgmp.personnel.model.dto.mapper.StructureMapper;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StructureService implements StructureIservice {
    private final StructureRepository strRepo;
    private final StructureMapper strMapper;
    private final TypeRepository typeRepo;
    private final HistoStructureIservice histoService;

    //Enregistrement d'une structure père
    @Override @Transactional
    public void saveStructure(StructureReqDto structureReqDto) throws UnknownHostException {
        Structure structure= strMapper.mapToStructure(structureReqDto);

        structure= strRepo.save(structure);
        structure.setTutelleDirecte(structure.getTutelleDirecte() == null ? null : strRepo.findById(structure.getTutelleDirecte().getStrId()).orElse(null));
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
    public List<TreeviewDto> getTreeviewDtos(Long strId)
    {
        Structure str = strRepo.findById(strId).orElse(null);
        if(str == null) return new ArrayList<>();
        str = this.loadChildrenTree(str);
        TreeviewDto treeviewDto = new TreeviewDto();
        treeviewDto.setText(str.toString());
        treeviewDto.setHref("/parametres/structure/detail?strId=" + str.getStrId());
        treeviewDto.setTags("['" + this.getInverseLeve(str) + "']");
        treeviewDto.setNodes(str.getStructuresSousTutelle().stream().flatMap(fils->getTreeviewDtos(fils.getStrId()).stream()).collect(Collectors.toList()));
        return Arrays.asList(treeviewDto);
    }

    @Override
    public List<TreeviewDto> getTreeviewRoot()
    {
        List<Structure> structures = strRepo.findByStrLevel(1);
        return structures.stream().flatMap(str->this.getTreeviewDtos(str.getStrId()).stream()).collect(Collectors.toList());
    }



    @Override
    public List<Structure> getStructureByIdNul() {
        return strRepo.findStructureByTutelleDirecteIsNull();
    }

    @Override
    public List<Type> getTypesStructures() {
        return typeRepo.getTypesFils("STR");
    }

    @Override
    public int getInverseLeve(Structure str)
    {
        Structure loadedStr  = this.loadChildrenTree(str);
        return (int) this.getAllSousStructure(str.getStrId()).stream().map(fils->fils.getStrLevel()).distinct().count()-1;
    }



}
