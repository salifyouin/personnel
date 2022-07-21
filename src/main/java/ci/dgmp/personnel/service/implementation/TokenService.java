package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.security.model.dao.SecurityTokenRepo;
import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.SecurityToken;
import ci.dgmp.personnel.service.exception.AppException;
import ci.dgmp.personnel.service.interfac.ITokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor //@Profile("deprecated")
public class TokenService implements ITokenService
{
    private final SecurityTokenRepo stkRepo;
    private final UserRepository userRepo;
    @Override
    public SecurityToken generateToken(AppUser user)
    {
        SecurityToken securityToken;
        if(stkRepo.existsByUser(user.getUserId())) securityToken = stkRepo.findByByUser(user.getUserId());
        else securityToken = SecurityToken.builder().appUser(user).createdAt(LocalDateTime.now()).build();
        securityToken.setToken(UUID.randomUUID().toString());
        securityToken.setAlreadyUsed(false);
        securityToken.setTokenExpirationDate(LocalDateTime.now().plusHours(24));
        securityToken.setUpdatedAt(LocalDateTime.now());
        return stkRepo.save(securityToken);
    }

    @Override
    public SecurityToken generateToken(String userEmail)
    {
        AppUser user = userRepo.findByUserEmail(userEmail).orElseThrow(()->new AppException("Adresse mail introuvable"));
        return this.generateToken(user);
    }
}
