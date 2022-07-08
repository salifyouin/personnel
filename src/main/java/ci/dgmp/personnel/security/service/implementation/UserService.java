package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.UserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.UserMapper;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.projection.AppUserInfo;
import ci.dgmp.personnel.security.service.interfac.UserIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserIservice {
private final UserRepository userRepo;
private final UserMapper userMapper;
private final PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserReqDto userReqDto) {
        AppUser user = userMapper.mapToUser(userReqDto);
        user.setUserActive(true);
        user.setCreatedAt(LocalDateTime.now());
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
}
