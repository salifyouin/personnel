package ci.dgmp.personnel.controller;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StructureRestController
{
    private final StructureIservice strService;
    private final StructureRepository strRep;

    @GetMapping(path = "/structures/loadChildrenTree/{strId}")
    public Structure loadChildrenTree(@PathVariable Long strId)
    {
        Structure structure = strRep.findById(strId).orElse(null);
        return structure == null ? null : strService.loadChildrenTree(structure);
    }

}
