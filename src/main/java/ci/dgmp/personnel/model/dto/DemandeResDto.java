//package ci.dgmp.personnel.model.dto;
//
//import ci.dgmp.personnel.model.entities.Agent;
//import ci.dgmp.personnel.model.entities.Demande;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.BeanUtils;
//
//import java.time.LocalDateTime;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Builder
//public class DemandeResDto {
//    private Long demId;
//    private String demObjet;
//    private LocalDateTime demDateSaisie;
//    private LocalDateTime demDateDebut;
//    private LocalDateTime demDateFin;
//    private long demNbrJour;
//    private long demAgtId;
//    private long demTypId;
//    private String matriculeAgent;
//    private String nomAgent;
//    private String prenomAgent;
//    private String fonctionAgent;
//    private String structueAgent;
//    private String typeDemande;
//    private String critere;
//
//    public DemandeResDto (Demande demande) {
//        BeanUtils.copyProperties(demande, this);
//    }
//}
