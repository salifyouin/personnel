package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.dto.request.ActiveUserAccountDto;
import ci.dgmp.personnel.security.model.dto.request.ChangePasswordDto;
import ci.dgmp.personnel.security.model.dto.request.ResetUserAccountDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.projection.AppUserInfo;

import java.util.List;

public interface UserIservice {
    void saveUser(UserReqDto userReqDto);
    List<AppUserInfo> getAllUsers();
    void deleteAgent(Long userId);
    void updateUserProfile(AppUser user);
    void changePassWord(ChangePasswordDto dto);
    void activatedAccount(ActiveUserAccountDto dto);
    void resetPassword(ResetUserAccountDto dto);

}
