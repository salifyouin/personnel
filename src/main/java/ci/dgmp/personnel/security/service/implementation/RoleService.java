package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.model.dto.AgentReqDto;
import ci.dgmp.personnel.model.entities.Agent;
import ci.dgmp.personnel.model.entities.Structure;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.security.model.dao.RoleRepository;
import ci.dgmp.personnel.security.model.dto.mapper.RoleMapper;
import ci.dgmp.personnel.security.model.dto.request.RoleReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.dto.responses.RoleResDto;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.projection.RoleInfo;
import ci.dgmp.personnel.security.service.interfac.RoleIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleIservice {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Page<RoleResDto> getAllRoles(int page, int size) {
        List<RoleResDto> allRoles =roleRepository.getAllRoles(PageRequest.of(page,size));
        return new PageImpl<>(allRoles,PageRequest.of(page,size),roleRepository.count());
    }

    @Override
    public Page<RoleResDto> getAllRoles(String critere, int page, int size) {
        List<RoleResDto> allRoles =roleRepository.getAllRoles(critere, PageRequest.of(page,size));
        return new PageImpl<>(allRoles,PageRequest.of(page,size),roleRepository.count());
    }

    @Override
    public List<RoleInfo> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    @Override
    public void saveRole(RoleReqDto roleReqDto) {
        Role role =new Role();
        role=roleMapper.mapToRole(roleReqDto);
        role.setRoleActif(true);
        role.setCreatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }

    @Override
    public void saveRole(Role role) {
        role.setRoleActif(true);
        role.setCreatedAt(LocalDateTime.now());
        roleRepository.save(role);
    }


    @Override
    public void updateRole(RoleReqDto roleReqDto) {

    }

    @Override
    public void deleteRole(Long id) {

    }
}
