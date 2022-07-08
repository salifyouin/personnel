package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.PrivilegeToRoleRepository;
import ci.dgmp.personnel.security.model.dao.RoleRepository;
import ci.dgmp.personnel.security.model.dto.mapper.PrivilegeToRoleMapper;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToRoleReqDto;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeToUserReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeToRoleResDto;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.PrivilegeToUser;
import ci.dgmp.personnel.security.model.projection.PrivilegeToRoleInfo;
import ci.dgmp.personnel.security.service.interfac.PrivilegeToRoleIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeToRoleService implements PrivilegeToRoleIservice {
    private final PrivilegeToRoleRepository ptrRepo;
    private final RoleRepository roleRepo;
    private final PrivilegeToRoleMapper ptrMapper;

    @Override
    public void addPrivillegeToRole(PrivilegeToRoleReqDto privilegeToRoleReqDto) {
        PrivilegeToRole ptr = ptrMapper.mapToPrivilegeToRole(privilegeToRoleReqDto);
        ptr.setAssActive(1L);
        ptr.setAssDateDebut(LocalDate.now());
        ptr.setAssDateFin(LocalDate.now().plusYears(1));
        ptrRepo.save(ptr);
    }

    @Override
    public List<PrivilegeToRoleInfo> getPrivillegeByRole(Long roleId) {
        return ptrRepo.findByRole_RoleIdOrderByAssIdDesc(roleId);
    }

    @Override
    public List<PrivilegeToRoleInfo> getActivePrivilgeToRoleAss(Long roleId) {
        return ptrRepo.getActivePrivilgeToRoleAss(roleId);
    }

    @Override
    public void desablePrivilege(PrivilegeToRole ptr) {
        ptr.setAssActive(0L);
        ptrRepo.save(ptr);
    }

    @Override
    //Revoquer un privilege a un role
    public void revokePrivilegeToRole(Long roleId, Long prvId) {
      PrivilegeToRoleReqDto ptrDto= PrivilegeToRoleReqDto.builder()
               .roleId(roleId)
               .privilegeId(prvId)
              .build();
        PrivilegeToRole ptr = ptrMapper.mapToPrivilegeToRole(ptrDto);
        if (ptrRepo.existsPrivilegeForRole(roleId,prvId)){
            ptr =ptrRepo.getPrivilegeForRole(roleId,prvId);
        }
        ptr.setAssActive(0L);
        ptrRepo.save(ptr);
    }
}
