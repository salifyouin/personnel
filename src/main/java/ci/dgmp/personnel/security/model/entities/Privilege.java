package ci.dgmp.personnel.security.model.entities;

import ci.dgmp.personnel.security.model.dto.validators.NoneExistingPrivilegeCode;
import ci.dgmp.personnel.security.model.dto.validators.NoneExistingPrivilegeName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@ToString
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilegeId;
    @NotBlank(message = "Le code privilège est obligatoir")
    @NotNull(message = "Le code privilège est obligatoire")
    @NoneExistingPrivilegeCode
    private String privilegeCode;
    @NoneExistingPrivilegeName
    @NotBlank(message = "Le privilège est obligatoir")
    @NotNull(message = "Le privilège est obligatoire")
    private String privilegeName;
    private boolean privilegeActif;
    private LocalDateTime privilegeCretatedAt;
    private LocalDateTime privilegeUpdatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privilege privilege = (Privilege) o;
        return privilegeId.equals(privilege.privilegeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilegeId);
    }
}
