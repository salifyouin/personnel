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
@DiscriminatorValue("RoleToUser")
public class RoleToUser extends Assignation{
    @ManyToOne @JoinColumn(name = "roleId")
    private Role role;
    @ManyToOne @JoinColumn(name = "userId")
    private AppUser appUser;
}
