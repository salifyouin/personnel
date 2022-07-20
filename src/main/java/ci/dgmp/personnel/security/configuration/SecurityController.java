package ci.dgmp.personnel.security.configuration;

import ci.dgmp.personnel.security.model.dto.request.ActiveUserAccountDto;
import ci.dgmp.personnel.security.model.dto.request.ChangePasswordDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.service.interfac.UserIservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public String saveUser(@Valid UserReqDto user, BindingResult br, Model model)
    {
        if(br.hasErrors())
        {
            br.getGlobalErrors().forEach(err->model.addAttribute(err.getDefaultMessage().split(":")[0], err.getDefaultMessage().split(":")[1]));
            br.getFieldErrors().forEach(err-> model.addAttribute(err.getField(), err.getDefaultMessage()));
            return register(model);
        }
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

    @RequestMapping("/accountActivation")
    private String accountActivation(Model model, @RequestParam String token)
    {
        ActiveUserAccountDto dto = ActiveUserAccountDto.builder().token(token).build();
        model.addAttribute("activeUserAccountDto", dto);
        return "accountActivation";
    }


    @PostMapping(path = "/acitvedAccount")//ce2eac6f-ee8c-4ad1-b75a-30510cfe5fac
    public String acitvedAccount(Model model,
                                 HttpServletRequest request, @Valid ActiveUserAccountDto dto, BindingResult br) throws ServletException {
        if(br.hasErrors())
        {
            br.getAllErrors();
            br.getGlobalErrors().forEach(err->{
                if(err.getDefaultMessage().indexOf(":")<0) model.addAttribute("globalError", err.getDefaultMessage());
                else  model.addAttribute(err.getDefaultMessage().split(":")[0], err.getDefaultMessage().split(":")[1]);});
            br.getFieldErrors().forEach(err-> model.addAttribute(err.getField(), err.getDefaultMessage()));
            return this.accountActivation(model, dto.getToken());
        }
        userService.activatedAccount(dto);
        request.logout();
        return "redirect:/login";
    }


}
