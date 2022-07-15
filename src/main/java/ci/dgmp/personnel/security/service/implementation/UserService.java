package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.UserMapper;
import ci.dgmp.personnel.security.model.dto.request.ActiveUserAccountDto;
import ci.dgmp.personnel.security.model.dto.request.ChangePasswordDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.projection.AppUserInfo;
import ci.dgmp.personnel.security.service.interfac.UserIservice;
import ci.dgmp.personnel.service.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserIservice {
private final UserRepository userRepo;
private final UserMapper userMapper;
private final PasswordEncoder passwordEncoder;
private final SecurityContextService scs;
    @Override
    public void saveUser(UserReqDto userReqDto) {
        AppUser user = userMapper.mapToUser(userReqDto);
         user.setUserActive(true);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepo.save(user);
    }

    @Override
    public List<AppUserInfo> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public void deleteAgent(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public void updateUserProfile(AppUser user)
    {
        AppUser loadedUser = userRepo.findById(user.getUserId()).orElseThrow(()->new AppException("Utilisateur introuvable"));
        if(!passwordEncoder.matches(user.getUserPassword(), loadedUser.getUserPassword())) throw new AppException("Mot de passe incorrect");
        loadedUser.setUserNom(user.getUserNom());
        loadedUser.setUserPrenom(user.getUserPrenom());
        loadedUser.setUserLogin(user.getUserLogin());
        loadedUser.setUserTelephone(user.getUserTelephone());
        loadedUser.setUserEmail(user.getUserEmail());
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(loadedUser);
        scs.refreshSecurityContext();
    }

    @Override @Transactional
    public void changePassWord(ChangePasswordDto dto)
    {
        //Recuperer l'utilisateur connecte
        AppUser authUser = userRepo.findByUserLogin(scs.getAuthUsername()).get() ;
        authUser.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
        userRepo.save(authUser);
    }


    @Override
    public void activatedAccount(ActiveUserAccountDto dto) {
        AppUser authUser = userRepo.findByUserLogin(scs.getAuthUsername()).get() ;
        authUser.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
        userRepo.save(authUser);
    }
}
