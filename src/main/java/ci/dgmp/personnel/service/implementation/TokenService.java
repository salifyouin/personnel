package ci.dgmp.personnel.service.implementation;

import ci.dgmp.personnel.security.model.dao.SecurityTokenRepo;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.SecurityToken;
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
    @Override
    public SecurityToken generateToken(AppUser user)
    {
        SecurityToken securityToken = SecurityToken.builder()
                .appUser(user)
                .token(UUID.randomUUID().toString())
                .tokenExpirationDate(LocalDateTime.now().plusHours(24))
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();
        return stkRepo.save(securityToken);
    }
}
