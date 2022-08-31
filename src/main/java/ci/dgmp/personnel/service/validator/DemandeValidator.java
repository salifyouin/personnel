//package ci.dgmp.personnel.service.validator;
//
//import ci.dgmp.personnel.model.dao.DemandeRepository;
//import ci.dgmp.personnel.model.dto.DemandeReqDto;
//import ci.dgmp.personnel.service.exception.AppException;
//import ci.dgmp.personnel.service.exception.ErrorMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class DemandeValidator {
//    private final DemandeRepository demandeRepository;
//    public void validateCreateDemande(DemandeReqDto demande) {
//        if(demande.getDemObjet()==null || demande.getDemObjet().trim().equalsIgnoreCase("")) throw new AppException(ErrorMessage.DEM_OBJET_VIDE);
//    }
//}
