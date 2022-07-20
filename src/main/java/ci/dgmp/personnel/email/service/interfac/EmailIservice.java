package ci.dgmp.personnel.email.service.interfac;

import ci.dgmp.personnel.email.model.dto.EmailDto;
import ci.dgmp.personnel.email.model.entities.Email;
import ci.dgmp.personnel.security.model.entities.AppUser;
import org.springframework.scheduling.annotation.Async;

public interface EmailIservice {

    @Async
    void sendEmail(String senderMail, String receiverMail, String mailObject, String message) throws IllegalAccessException;
    Email sendActivationEmail(AppUser user) throws IllegalAccessException;

}
