package ci.dgmp.personnel.model.projection;

import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;

public interface StructureInfo {
    Long getStrId();

    String getStrCode();

    String getStrLibelle();

    String getStrSigle();

    StructureInfo getTutelleDirecte();

    TypeInfo getType();

    interface StructureInfos {
        Long getStrId();

        String getStrCode();

        String getStrLibelle();

        String getStrSigle();

        Structure getTutelleDirecte();

        Type getType();
    }

    interface TypeInfo {
        Long getTypId();

        String getTypCode();

        String getTypLibelle();

        Type getType();
    }
}
