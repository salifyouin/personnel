package ci.dgmp.personnel.web;

import ci.dgmp.personnel.model.dao.TypeRepository;
import ci.dgmp.personnel.model.entities.Type;
import ci.dgmp.personnel.service.exception.AppException;
import ci.dgmp.personnel.service.interfac.TypeIservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parametres/types")
@PreAuthorize("isAuthenticated()")
public class TypeController {
    private final TypeIservice typeService;
    private final TypeRepository typeRepository;

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size){
        Page<Type> typePageParents=typeService.getAllPagesType(page,size);
        //List<Type> typeParents=typeRepository.findByType_TypIdIsNull();
        model.addAttribute("typePageParents", typePageParents);
        model.addAttribute("type",new Type());
        return "pages/type/indexTypePere";
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/detail")
    public String indexDetail(Model model,@RequestParam Long idPere
//                        @RequestParam(defaultValue = "0") int page,
//                        @RequestParam(defaultValue = "10") int size
    ){
        //Page<Type> typePageDetailsByParentId=typeService.getDetailsByParentId(1L,page,size);
        //List<Type> typePageDetailsByParentId=typeRepository.findTypeByType_TypId(1l);
        Type typePere=typeRepository.findById(idPere).orElseThrow(()->new AppException("Type introuvable"));
        Type type = Type.builder().typCode(typePere.getTypCode()).type(typePere).build();
        model.addAttribute("type",type);// ici
        List<Type> listedetail=typeRepository.findByType_TypIdOrderByTypIdDesc(typePere.getTypId());
        model.addAttribute("listedetail", listedetail);
        model.addAttribute("type", type);
        return "pages/type/indexTypeFils";
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @PostMapping(path = "/save")
    public String saveType(Type type){
       typeRepository.save(type);
        Page<Type> typePageParents=typeService.getAllPagesType(0,10);
        return "redirect:/parametres/types/index";
    }

    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @PostMapping(path = "/saveDetail")
    public String saveDetailType(Type t){
        typeRepository.save(t);
        Page<Type> typePageParents=typeService.getAllPagesType(0,10);
        return "redirect:/parametres/types/detail?idPere="+t.getType().getTypId();
    }


    @PreAuthorize("hasAnyAuthority('DEV','ADMIN')")
    @GetMapping("/fonction/index")
    private String indexFonction(){
        return "pages/fonction/indexFonction";
    }
}
