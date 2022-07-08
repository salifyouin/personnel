package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import ci.dgmp.personnel.security.model.projection.PrivilegeToUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrivilegeToUserRepository extends JpaRepository<PrivilegeToUser, Long> {
    @Query("select (count(p) > 0) from PrivilegeToUser p where p.appUser.userId = :userId and p.privilege.privilegeId = :privilegeId and p.structure.strId = :strId and p.assActive = 1 and coalesce(p.assDateDebut, current_date ) <= current_date and coalesce(p.assDateFin, current_date ) >= current_date")
    boolean userHasDirectPrivilege(@Param("userId") Long userId, @Param("privilegeId") Long privilegeId, @Param("strId") Long strId);

    @Query("select p from PrivilegeToUser p where p.appUser.userId = ?1 and p.assActive = 1 order by p.assId DESC")
    List<PrivilegeToUserInfo> getPrivilegeByUser(Long userId);

    @Query("select p from PrivilegeToUser p where p.appUser.userId = ?1 and p.structure.strId = ?2 and p.assActive = 1 and coalesce(p.assDateDebut, current_date ) <= current_date and coalesce(p.assDateFin, current_date ) >= current_date")
    List<PrivilegeToUser> getDirectPrivilegeAssForUser(Long userId, Long strId);

    @Query("select p from PrivilegeToUser p where p.appUser.userId = ?1 and p.structure.strId = ?2 and p.assActive = 2")
    List<PrivilegeToUser> getRevokedPrivilegeAssForUser(Long userId, Long strId);

    @Query("select (count(p) > 0) from PrivilegeToUser p where p.appUser.userId = ?1 and p.privilege.privilegeId = ?2 and p.structure.strId = ?3 and p.assActive = 2")
    boolean isPrivilegeRevokedForUser(Long userId, Long privilegeId, Long strId);

    @Query("select p from PrivilegeToUser p where p.appUser.userId = ?1 and p.privilege.privilegeId = ?2 and p.structure.strId = ?3")
    PrivilegeToUser getPrivilegeForUser(Long userId, Long privilegeId, Long strId); //Récupère le privilège d'un utilisateur sur une structure

    @Query("select (count(p) > 0) from PrivilegeToUser p where p.appUser.userId = ?1 and p.privilege.privilegeId = ?2 and p.structure.strId = ?3")
    boolean existsPrivilegeForUser(Long userId, Long privilegeId, Long strId); //vérifie si cette assignation de privilège existe pour l'utilisateur dans une structure




}
