package ci.dgmp.personnel.email.service.implementation;

import ci.dgmp.personnel.AppConfiguration;
import ci.dgmp.personnel.email.model.dao.EmailRepository;
import ci.dgmp.personnel.email.model.dto.EmailDto;
import ci.dgmp.personnel.email.model.dto.mapper.EmailMapper;
import ci.dgmp.personnel.email.model.entities.Email;
import ci.dgmp.personnel.email.service.interfac.EmailIservice;
import ci.dgmp.personnel.email.service.interfac.HTMLEmailBuilder;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.SecurityToken;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import ci.dgmp.personnel.service.exception.AppException;
import ci.dgmp.personnel.service.interfac.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final UserRepository userRepo;
    private final ITokenService tokenService;

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
    public Email sendActivationEmail(AppUser user, String token) throws IllegalAccessException {
        AppUser authUser = scs.getAuthUser();
        Email email = emailMapper.mapToEmailDto(user);
        email.setToken(token);
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
        this.sendEmail(appConfig.getSystemMailSender(),
                user.getUserEmail(),
                appConfig.getSystemMailActivationObject(),
                htmlEmailBuilder.buildAccountActivationHTMLEmail(user.getUserNom()+" "+user.getUserPrenom(), activationLink));
        email.setSent(true);
        return emailRepo.save(email);
    }

    @Override @Transactional
    public Email sendRestEmail(String userEmail) throws IllegalAccessException {
        //AppUser authUser = scs.getAuthUser();
        AppUser user = userRepo.findByUserEmail(userEmail).orElseThrow(()->new AppException("Adresse mail introuvable"));
        SecurityToken token = tokenService.generateToken(user);
        Email email = emailMapper.mapToEmailDto(user);
        email.setToken(token.getToken());
        email.setMailObject(appConfig.getSystemMailResetObject());
        email.setMailMessage("");
        email.setSystemMailSender(appConfig.getSystemMailSender());
        email.setSenderUserId(user.getUserId());
        email.setSenderUsername(user.getUserLogin());
        email.setSenderAgentId(user.getAgent()==null?null:user.getAgent().getAgtId());
        /**
         * Envoie de mail
         */
        String resetingLink = appConfig.getAppAddress() + "/resetAccount?token=" + email.getToken();
        this.sendEmail(appConfig.getSystemMailSender(),
                user.getUserEmail(),
                appConfig.getSystemMailResetObject(),
                htmlEmailBuilder.buildPasswordReinitialisationHTMLEmail(user.getUserNom()+" "+user.getUserPrenom(),resetingLink));
        email.setSent(true);
        return emailRepo.save(email);
    }
}
