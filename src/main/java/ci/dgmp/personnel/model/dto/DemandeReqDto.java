package ci.dgmp.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DemandeReqDto {
    private String demObjet;
    private LocalDateTime demDateSaisie;
    private LocalDate demDateDebut;
    private LocalDate demDateFin;
    private long demNbrJour;
    private long demAgtId;
    private String demType;
}
