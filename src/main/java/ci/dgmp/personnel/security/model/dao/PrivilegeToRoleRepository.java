package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import ci.dgmp.personnel.security.model.projection.PrivilegeToRoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PrivilegeToRoleRepository extends JpaRepository<PrivilegeToRole, Long> {
    @Query("select new ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto(p) from PrivilegeToRole p where p.role.roleId = ?1 order by p.assId DESC")
    List<PrivilegeToRoleResDto> getPrivillegeByRole(Long roleId);

    @Query("select p from PrivilegeToRole p where p.role.roleId = ?1")
    List<PrivilegeToRole> findByRole_RoleId(long roleId);

    //List<PrivilegeToRoleInfo> findByRole_RoleIdAndAssActiveIsTrueOrderByAssIdDesc(long roleId);

    List<PrivilegeToRoleInfo> findByRole_RoleIdOrderByAssIdDesc(long roleId);


    @Query("select (count(p) > 0) from PrivilegeToRole p where p.role.roleId = ?1 and p.privilege.privilegeId = ?2 and p.assActive = true and coalesce(p.assDateDebut, current_date) <= current_date and coalesce(p.assDateFin, current_date ) >= current_date ")
    boolean roleHasPrivilege(long roleId, Long privilegeId);

    @Query("select (count(p) > 0) from PrivilegeToRole p where p.role.roleId = ?1 and p.privilege.privilegeId = ?2")
    boolean existsPrivilegeForRole(long roleId, Long privilegeId);

    @Query("select p from PrivilegeToRole p where p.role.roleId = ?1 and p.privilege.privilegeId = ?2")
    PrivilegeToRole getPrivilegeForRole(long roleId, Long privilegeId);

    @Query("select p from PrivilegeToRole p where p.role.roleId = ?1 and p.assActive = true and coalesce(p.assDateDebut, current_date ) <= current_date and coalesce(p.assDateFin, current_date ) >= current_date order by p.assId DESC")
    List<PrivilegeToRoleInfo> getActivePrivilgeToRoleAss(long roleId);









}
