package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.RoleToUserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.RoleToUserMapper;
import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.model.projection.RoleToUserInfo;
import ci.dgmp.personnel.security.service.interfac.RoleToUserIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleToUserService implements RoleToUserIservice {
    private final RoleToUserRepository rtuRepo;
    private final RoleToUserMapper rtuMapper;
    @Override
    public List<RoleToUserInfo> getRoleByUser(Long userId) {
        return rtuRepo.getRoleByUser(userId);
    }

    @Override
    public List<RoleToUserInfo> getNoneAssignationForUser(Long userId) {
        return rtuRepo.getNoneAssignationForUser(userId);
    }

    @Override
    @Transactional
    public void addRoleToUser(RoleToUserReqDto dto) {
        rtuRepo.disableActiveRolesForUser(dto.getUserId());
        RoleToUser utr =rtuMapper.mapToRoleToUser(dto);
        utr.setAssActive(true);
        rtuRepo.save(utr);
    }
}
