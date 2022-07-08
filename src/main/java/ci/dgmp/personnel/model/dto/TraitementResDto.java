package ci.dgmp.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TraitementResDto {
    private Long traitId;
    private LocalDateTime traitDate;
    private String traitObservation;
    private Long traiDemId;
    private Long traiTypId;
    private Long traiAgtId;
    private String traiAgtNom;
    private String traiAgtPrenom;
    private String traiAgtFonction;
    private String traiStatut;
}
