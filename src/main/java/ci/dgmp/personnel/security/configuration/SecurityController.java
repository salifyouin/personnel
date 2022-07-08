package ci.dgmp.personnel.security.configuration;

import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.service.interfac.UserIservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SecurityController {
    private final UserIservice userService;
    @RequestMapping("/login")
    public String login(){
       return "login";
    }

    @RequestMapping("/register")
    private String register(Model model){
        model.addAttribute("user",new UserReqDto());
        return "register";
    }

    @PostMapping(path = "/save")
    public String saveUser(UserReqDto user){
        userService.saveUser(user);
        return "redirect:/login";
    }


    @RequestMapping("/")
    @PreAuthorize("isAuthenticated()")//Cette url est accessible que losque je suis authentifié
    public String home(){
        return "pages/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String Authorization() {
        return "/403";
    }



}
