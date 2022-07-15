package ci.dgmp.personnel.service.interfac;

import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.SecurityToken;

public interface ITokenService
{
    SecurityToken generateToken(AppUser user);
}
