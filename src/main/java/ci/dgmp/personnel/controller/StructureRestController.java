package ci.dgmp.personnel.controller;

import ci.dgmp.personnel.model.dao.AgentRepository;
import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.report.interfac.IReportExporter;
import ci.dgmp.personnel.security.service.constant.ImgConstants;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StructureRestController
{
    private final StructureIservice strService;
    private final StructureRepository strRep;
    private final AgentRepository agentRepository;
    private final IReportExporter reportExporter;
    private final StructureRepository typeRepo;

    @GetMapping(path = "/structures/loadChildrenTree/{strId}")
    public Structure loadChildrenTree(@PathVariable Long strId)
    {
        Structure structure = strRep.findById(strId).orElse(null);
        return structure == null ? null : strService.loadChildrenTree(structure);
    }

    @GetMapping(path = "/printAllAgents", produces = {MediaType.APPLICATION_PDF_VALUE})
    public File printAllAgentsByStr(@RequestParam Long strId) throws JRException, SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("str_id", strId);
        List<Agent> agents = agentRepository.getAgents(strId);
        return reportExporter.exportReport(ImgConstants.staticDirectory + "/report/listPersonnel.jrxml", params);
    }

//    @GetMapping("/structures/getStrMereByTypeLevel")
//    public List<Structure> getStrMereByTypeLevel(@RequestParam Long level)
//    {
//        return typeRepo.getStrMereByTypeLevel(level);
//    }

}
