package ci.dgmp.personnel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeResDto {
    private Long typId;
    private String typLibelle;
    private Long typTypId;
}
