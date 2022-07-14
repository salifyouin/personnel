package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.projection.AppUserInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("select a from AppUser a where a.userActive = true order by a.userId DESC")
    List<AppUserInfo> getAllUsers();

    //avec Fin Instance
    Optional <AppUser> findByUserLogin(String userLogin);

    boolean existsByUserLogin(String userLogin);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserTelephone(String userTelephone);

    @Query("select a.userPassword from AppUser a where a.userLogin = ?1")
    String getUserPassword(String userLogin);








}
