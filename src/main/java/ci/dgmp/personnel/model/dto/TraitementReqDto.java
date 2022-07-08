package ci.dgmp.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TraitementReqDto {
    private LocalDateTime traitDate;
    private String traitObservation;
    private Long traiDemId;
    private Long traiTypId;
    private Long traiAgtId;
    private String traiStatut;
}
