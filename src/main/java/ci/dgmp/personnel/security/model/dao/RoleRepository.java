package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.dto.responses.RoleResDto;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.model.projection.RoleInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select new ci.dgmp.personnel.security.model.dto.responses.RoleResDto(r) " +
            "from Role r " +
            "where r.roleActif = true " +
            "order by r.roleId DESC")
    List<RoleResDto> getAllRoles(Pageable pageable);

    @Query("select new ci.dgmp.personnel.security.model.dto.responses.RoleResDto(r) " +
            "from Role r" +
            " where r.roleActif = true " +
            "order by r.roleId DESC")
    List<RoleResDto> getAllRoles(@Param("critere") String critere, Pageable pageable);

    @Query("select r from Role r where r.roleActif = true order by r.roleName")
    List<RoleInfo> getAllRoles();

    @Query("select r.role from RoleToUser r where r.appUser.userId = ?1 and coalesce(r.assDateDebut, current_date ) <= current_date and coalesce(r.assDateFin, current_date ) >= current_date and r.assActive = true")
    Role getActiveRoleForUser(Long userId);
    Role findByRoleCode(String user);
}

