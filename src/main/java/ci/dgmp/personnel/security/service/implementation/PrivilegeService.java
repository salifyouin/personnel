package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.model.dao.StructureRepository;
import ci.dgmp.personnel.model.dto.AgentResDto;
import ci.dgmp.personnel.security.model.dao.*;

import ci.dgmp.personnel.security.model.dto.mapper.PrivMapper;
import ci.dgmp.personnel.security.model.dto.request.PrivilegeReqDto;
import ci.dgmp.personnel.security.model.dto.responses.PrivilegeResDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.service.interfac.PrivilegeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PrivilegeService implements PrivilegeIservice {
    private  final PrivilegeRepository privilegeRepository;
    private final PrivMapper privilegeMapper;
    private final PrivilegeToUserRepository ptuRepo;
    private final RoleToUserRepository rtuRepo;
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final StructureRepository strRepo;
    @Override
    public void savePrivilege(PrivilegeReqDto privilegeReqDto) {
        Privilege privilege = new Privilege();
        privilege=privilegeMapper.mapToPrivilege(privilegeReqDto);
        privilege.setPrivilegeCretatedAt(LocalDateTime.now());
        privilegeRepository.save(privilege);
    }

    @Override
    public List<PrivilegeResDto> getAllPrivileges() {
        return privilegeRepository.getAllPrivileges();
    }

    @Override
    public Page<PrivilegeResDto> getAllPrivileges(int page, int size) {
        List<PrivilegeResDto> allPrivileges =privilegeRepository.getAllPrivileges(PageRequest.of(page,size));
        return new PageImpl<>(allPrivileges,PageRequest.of(page,size),privilegeRepository.count());
    }

    @Override
    public Page<PrivilegeResDto> getAllPrivileges(String critere, int page, int size) {
        List<PrivilegeResDto> allPrivileges =privilegeRepository.getAllPrivileges(critere, PageRequest.of(page,size));
        return new PageImpl<>(allPrivileges,PageRequest.of(page,size),privilegeRepository.count());
    }

    @Override
    public void savePrivilege(Privilege privilege) {
        privilege.setPrivilegeActif(true);
        privilege.setPrivilegeCretatedAt(LocalDateTime.now());
        privilegeRepository.save(privilege);
    }

    @Override
    public List<Privilege> getUserPrivileges(Long userId, Long strId)
    {
        if(!userRepo.existsById(userId) || !strRepo.existsById(strId)) return new ArrayList<>();

        List<Privilege> privilegesDirects = privilegeRepository.getDirectPrivilegeForUser(userId, strId);

        List<Privilege> privilegesForRoleActive = new ArrayList<>();

        RoleToUser activeAss = rtuRepo.getActiveAssignationForUser(userId);

        if(activeAss!=null)
        {
            if(activeAss.getStructure() != null)
            {
                if(activeAss.getStructure().getStrId().equals(strId))
                    privilegesForRoleActive = privilegeRepository.getActivePrivilegesForRole(activeAss.getRole().getRoleId());
            }
        }


        List<Privilege> revokedPrivileges = privilegeRepository.getRevokedPrivilegeForUser(userId, strId);

        return Stream.concat(privilegesDirects.stream(), privilegesForRoleActive.stream())
                .distinct()
                .filter(prv->revokedPrivileges.stream().noneMatch(rvk->prv.equals(rvk)))
        .collect(Collectors.toList());
    }

}
