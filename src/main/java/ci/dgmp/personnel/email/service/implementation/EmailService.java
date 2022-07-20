package ci.dgmp.personnel.email.service.implementation;

import ci.dgmp.personnel.AppConfiguration;
import ci.dgmp.personnel.email.model.dao.EmailRepository;
import ci.dgmp.personnel.email.model.dto.EmailDto;
import ci.dgmp.personnel.email.model.dto.mapper.EmailMapper;
import ci.dgmp.personnel.email.model.entities.Email;
import ci.dgmp.personnel.email.service.interfac.EmailIservice;
import ci.dgmp.personnel.email.service.interfac.HTMLEmailBuilder;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class EmailService implements EmailIservice {
    private final EmailRepository emailRepo;
    private final EmailMapper emailMapper;
    private final AppConfiguration appConfig;
    private final ISecurityContextService scs;
    private final JavaMailSender javaMailSender;
    private final HTMLEmailBuilder htmlEmailBuilder;

    @Override
    @Async
    public void sendEmail(String senderMail, String receiverMail, String mailObject, String message) throws IllegalAccessException {
        try
        {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(message, true); // Second parameter true means that the message will be an HTML message
            mimeMessageHelper.setTo(receiverMail);
            mimeMessage.setSubject(mailObject);
            mimeMessage.setFrom(senderMail);
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e)
        {
            throw new IllegalAccessException("Error while sending email");
        }
    }

    @Override
    public Email sendActivationEmail(AppUser user) throws IllegalAccessException {
        AppUser authUser = scs.getAuthUser();
        Email email = emailMapper.mapToEmailDto(user);
        email.setToken(UUID.randomUUID().toString());
        email.setMailObject(appConfig.getSystemMailActivationObject());
        email.setMailMessage("");
        email.setSystemMailSender(appConfig.getSystemMailSender());
        email.setSenderUserId(authUser.getUserId());
        email.setSenderUsername(authUser.getUserLogin());
        email.setSenderAgentId(authUser.getAgent()==null?null:authUser.getAgent().getAgtId());
        /**
         * Envoie de mail
         */
        String activationLink = appConfig.getAppAddress() + "/accountActivation?token=" + email.getToken();
        this.sendEmail(appConfig.getSystemMailSender(), user.getUserEmail(), appConfig.getSystemMailActivationObject(), htmlEmailBuilder.buildAccountActivationHTMLEmail(email.getUsername(), activationLink));
        email.setSent(true);
        return emailRepo.save(email);
    }
}
