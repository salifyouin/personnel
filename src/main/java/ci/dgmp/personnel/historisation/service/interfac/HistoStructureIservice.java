package ci.dgmp.personnel.historisation.service.interfac;

import ci.dgmp.personnel.historisation.entities.HistoStructure;
import ci.dgmp.personnel.model.entities.Structure;

import java.net.UnknownHostException;

public interface HistoStructureIservice{
    void saveHistoStructure(Structure structure, String action) throws UnknownHostException;
}
