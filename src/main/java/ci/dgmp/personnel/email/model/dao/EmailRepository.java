package ci.dgmp.personnel.email.model.dao;

import ci.dgmp.personnel.email.model.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
