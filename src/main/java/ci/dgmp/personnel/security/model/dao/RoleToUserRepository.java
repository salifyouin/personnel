package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.model.projection.RoleToUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoleToUserRepository extends JpaRepository<RoleToUser, Long> {
    @Query("select r from RoleToUser r where r.appUser.userId = ?1 and r.assActive = 1 order by r.assId DESC")
    List<RoleToUserInfo> getRoleByUser(Long userId);

    //Recupere toutes les assignation de roles actif de l'utilisateur
    @Query("select r from RoleToUser r where r.appUser.userId = ?1 and coalesce(r.assDateDebut, current_date ) <= current_date and coalesce(r.assDateFin, current_date ) >= current_date and r.assActive = 1")
    RoleToUser getActiveAssignationForUser(Long userId);

    //Combobox ajout d'un role a un utilisateur
    //Affiche les roles qui sont pas encore attribué a un utilisateur dans une structure
    @Query("select (count(r) > 0) from RoleToUser r where r.appUser.userId = ?1 and r.role.roleId = ?2 and r.structure.strId = ?3 and r.assActive = 1 and coalesce(r.assDateDebut, current_date ) <= current_date and coalesce(r.assDateFin, current_date ) >= current_date")
    boolean userHasRole(Long userId, long roleId, Long strId);

    //Avant d'attribuer un role a un utilisateur on desactive tout les autres roles actifs
    @Query("update RoleToUser rtu set rtu.assActive = 2 where rtu.appUser.userId = ?1 and rtu.assActive <> 3")
    @Modifying
    void disableActiveRolesForUser(Long userId);

    @Query("select r from RoleToUser r where r.appUser.userId = ?1 and r.assActive <> 3 order by r.assActive ASC ")
    List<RoleToUserInfo> getNoneRevokedAssForUser(Long userId);

    @Query("select (count(r) > 0) from RoleToUser r where r.appUser.userId = ?1 and r.role.roleId = ?2 and r.structure.strId = ?3")
    boolean existsRoleAssForUser(Long userId, long roleId, Long strId);

    @Query("select r from RoleToUser r where r.appUser.userId = ?1 and r.role.roleId = ?2 and r.structure.strId = ?3")
    RoleToUser getRoleAssForUser(Long userId, long roleId, Long strId);



}
