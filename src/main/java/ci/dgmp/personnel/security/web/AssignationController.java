package ci.dgmp.personnel.security.web;

import ci.dgmp.personnel.security.service.interfac.PrivilegeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AssignationController {

    @GetMapping("/assignation/index")
    public String index() {
        return "admin/assignation/index";
    }
}
