package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.entities.SecurityToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface SecurityTokenRepo extends JpaRepository<SecurityToken, Long> {
    @Query("select s from SecurityToken s where s.token = ?1")
    SecurityToken findByToken(String token);

    @Query("select (count(s) > 0) from SecurityToken s where s.token = ?1 and s.appUser.userEmail = ?2")
    boolean existsByTokenAndEmail(String token, String userEmail);

    @Query("select (count(s) > 0) from SecurityToken s where s.token = ?1 and s.tokenExpirationDate >= current_time ")
    boolean tokenIsNotExpired(String token);
}
