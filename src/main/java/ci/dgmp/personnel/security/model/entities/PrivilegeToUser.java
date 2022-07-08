package ci.dgmp.personnel.security.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@DiscriminatorValue("PrivilegeToUser")
public class PrivilegeToUser extends Assignation{
    @ManyToOne @JoinColumn(name = "userId")
    private AppUser appUser;
    @ManyToOne @JoinColumn(name = "privilegeId")
    private Privilege privilege;
}
