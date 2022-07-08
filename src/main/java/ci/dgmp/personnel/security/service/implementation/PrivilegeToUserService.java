package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.PrivilegeToRoleRepository;
import ci.dgmp.personnel.security.model.dao.PrivilegeToUserRepository;
import ci.dgmp.personnel.security.model.dto.mapper.PrivilegeToRoleMapper;
import ci.dgmp.personnel.security.model.dto.mapper.PrivilegeToUserMapper;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import ci.dgmp.personnel.security.service.interfac.PrivilegeIservice;
import ci.dgmp.personnel.security.service.interfac.PrivilegeToUserIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeToUserService implements PrivilegeToUserIservice {
    private final PrivilegeToUserRepository ptuRepo;
    private final PrivilegeToUserMapper ptuMapper;
    @Override
    public void addPrivilegeToUser(PrivilegeToUserReqDto ptuDto)
    {
        PrivilegeToUser ptu=ptuMapper.mapToPrivilegeToUser(ptuDto);
        if(ptuRepo.existsPrivilegeForUser(ptuDto.getUserId(), ptuDto.getPrivilegeId(), ptuDto.getAssStrId())) {
            ptu.setAssId(ptuRepo.getPrivilegeForUser(ptuDto.getUserId(), ptuDto.getPrivilegeId(), ptuDto.getAssStrId()).getAssId());
        }
        ptu.setAssActive(true);
        ptuRepo.save(ptu);
    }

    @Override
    public void revokePrivilegeToUser(PrivilegeToUserReqDto ptuDto)
    {
        PrivilegeToUser ptu1=ptuMapper.mapToPrivilegeToUser(ptuDto);
        if(ptuRepo.existsPrivilegeForUser(ptuDto.getUserId(), ptuDto.getPrivilegeId(), ptuDto.getAssStrId())) {
            ptu1.setAssId(ptuRepo.getPrivilegeForUser(ptuDto.getUserId(), ptuDto.getPrivilegeId(), ptuDto.getAssStrId()).getAssId());
        }
        ptu1.setAssActive(false);
        ptuRepo.save(ptu1);
    }

    @Override
    public void revokePrivilegeToUser(Long userId, Long prvId, Long strId)
    {
        PrivilegeToUserReqDto ptuDto = PrivilegeToUserReqDto.builder().userId(userId).privilegeId(prvId).assStrId(strId).build();
        PrivilegeToUser ptu = ptuMapper.mapToPrivilegeToUser(ptuDto);
        if(ptuRepo.existsPrivilegeForUser(userId, prvId, strId))
        {
            ptu = ptuRepo.getPrivilegeForUser(userId, prvId, strId);
        }
        ptu.setAssActive(false);
        ptuRepo.save(ptu);
    }
}
