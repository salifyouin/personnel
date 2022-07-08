package ci.dgmp.personnel.security.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@DiscriminatorValue("PrivilegeToRole")
public class PrivilegeToRole extends Assignation{
    @ManyToOne @JoinColumn(name = "privilegeId")
    private Privilege privilege;
    @ManyToOne @JoinColumn(name = "roleId")
    private Role role;
}
