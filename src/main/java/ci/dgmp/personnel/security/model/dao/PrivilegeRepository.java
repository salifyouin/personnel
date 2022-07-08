package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import ci.dgmp.personnel.security.model.projection.PrivilegeInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    @Query("select new ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto(p) " +
            "from Privilege p " +
            "where p.privilegeActif=true " +
            "and upper(concat(" +
            "coalesce(p.privilegeName,''), " +
            "coalesce(p.privilegeCode,'') )) " +
            "like upper(concat('%', :critere, '%')) " +
            " order by p.privilegeId DESC")
    List<PrivilegeResDto> getAllPrivileges(@Param("critere") String critere, Pageable pageable);

    @Query("select new ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto(p)  from Privilege p where p.privilegeActif =true order by p.privilegeId DESC")
    List<PrivilegeResDto> getAllPrivileges(Pageable pageable);


    @Query("select new ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto(p) from Privilege p where p.privilegeActif = true order by p.privilegeId DESC")
    List<PrivilegeResDto> getAllPrivileges();

    @Query("select p from Privilege p where p.privilegeActif = true order by p.privilegeName")
    List<PrivilegeInfo> getAllPrivilegess();

    //Liste des privileges actifs de l'utilisateur
    @Query("select p.privilege from PrivilegeToUser p where p.appUser.userId = ?1 and p.assActive = true and coalesce(p.assDateDebut, current_date ) <= current_date and coalesce(p.assDateFin, current_date ) >= current_date")
    List<Privilege> getActivePrivilegesForUser(long userId);

    //Liste des privileges actifs d'un role
    @Query("select p.privilege from PrivilegeToRole p where p.role.roleId = ?1 and p.assActive = true")
    List<Privilege> getActivePrivilegesForRole(long roleId);

    //Liste des priviles directe actifs de l'utilisateur dans une structure
    @Query("select p.privilege from PrivilegeToUser p where p.appUser.userId = ?1 and p.structure.strId = ?2 and p.assActive = true and coalesce(p.assDateDebut, current_date ) <= current_date and coalesce(p.assDateFin, current_date ) >= current_date")
    List<Privilege> getDirectPrivilegeForUser(Long userId, Long strId);

    //Liste des priviles revoque d'un utilisateur dans une structure
    @Query("select p.privilege from PrivilegeToUser p where p.appUser.userId = ?1 and p.structure.strId = ?2 and p.assActive = false")
    List<Privilege> getRevokedPrivilegeForUser(Long userId, Long strId);

    //Verifier si le code privilege existe deja
    boolean existsByPrivilegeCode(String prvCode);

    boolean existsByPrivilegeName(String privilegeName);








}
