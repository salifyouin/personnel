package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.RoleToUserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.RoleToUserMapper;
import ci.dgmp.personnel.security.model.dto.request.RoleToUserReqDto;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.model.projection.RoleToUserInfo;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
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
    private final ISecurityContextService scs;
    @Override
    public List<RoleToUserInfo> getRoleByUser(Long userId) {
        return rtuRepo.getRoleByUser(userId);
    }

    @Override
    public List<RoleToUserInfo> getNoneAssignationForUser(Long userId) {
        return rtuRepo.getNoneRevokedAssForUser(userId);
    }

    @Override
    @Transactional
    public void addRoleToUser(RoleToUserReqDto dto)
    {
        rtuRepo.disableActiveRolesForUser(dto.getUserId());
        RoleToUser rtu =rtuMapper.mapToRoleToUser(dto);
        if(rtuRepo.existsRoleAssForUser(dto.getUserId(), dto.getRoleId(), dto.getAssStrId()))
            rtu = rtuRepo.getRoleAssForUser(dto.getUserId(), dto.getRoleId(), dto.getAssStrId());
        rtu.setAssActive(1L);
        rtuRepo.save(rtu);
    }

    @Override
    @Transactional
    public void changeRoleForUser(Long userId, Long roleId, Long strId)
    {
        if(!rtuRepo.existsRoleAssForUser(userId, roleId, strId)) return;
        rtuRepo.disableActiveRolesForUser(userId);
        RoleToUser rtu = rtuRepo.getRoleAssForUser(userId, roleId, strId);
        rtu.setAssActive(1L);
        rtuRepo.save(rtu);
        scs.refreshSecurityContext();
    }
}
