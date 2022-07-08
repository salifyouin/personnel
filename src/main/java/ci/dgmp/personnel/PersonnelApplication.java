package ci.dgmp.personnel;

import ci.dgmp.personnel.model.dao.*;
import ci.dgmp.personnel.model.dto.DemandeReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.dto.mapper.IDemandeMapper;
import ci.dgmp.personnel.model.entities.*;
import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dao.PrivilegeToRoleRepository;
import ci.dgmp.personnel.security.model.dao.RoleRepository;
import ci.dgmp.personnel.security.model.dto.mapper.RoleMapper;
import ci.dgmp.personnel.security.model.dto.request.RoleReqDto;
import ci.dgmp.personnel.security.model.dto.request.UserReqDto;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.service.interfac.RoleIservice;
import ci.dgmp.personnel.security.service.interfac.UserIservice;
import ci.dgmp.personnel.service.interfac.StructureIservice;
import ci.dgmp.personnel.service.interfac.TypeIservice;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PersonnelApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect()
    {
        return new LayoutDialect();
    }


    //@Bean
    CommandLineRunner start(RoleIservice roleservice, TypeRepository typeRepository, AgentRepository agentRepository,
                            TypeIservice typeservice, StructureRepository structureRepository, RoleMapper roleMapper,
                            StructureIservice strService, DemandeRepository demandeRepository, IDemandeMapper demandeMapper,
                            PrivilegeToRoleRepository ptrRepo, PrivilegeRepository prvRepo, RoleRepository roleRepo, UserIservice userService) {
        return (args) ->
        {

//
//            System.out.println("============================");
//            prvRepo.getActivePrivilegesForRole(3L).forEach(System.out::println);
////            UserReqDto user = UserReqDto.builder()
////                    .userLogin("loss")
////                    .userActive(true)
////                    .userPassword("loss")
////                    .build();
////            userService.saveUser(user);
//            //Roles
//
//            //Type type31 = typeRepository.save(new Type(null,"Grade","A7",type30));
//            Role admin = roleRepo.save(new Role(1,"ADMIN","ADMIN",true,LocalDateTime.now(),null));
//            Role user = roleRepo.save(new Role(2,"USER","USER",true,LocalDateTime.now(),null));
//            Role dev = roleRepo.save(new Role(3,"DEV","DEV",true,LocalDateTime.now(),null));
//            Role agent = roleRepo.save(new Role(4,"AGENT","AGENT",true,LocalDateTime.now(),null));
//
////            Role role1=roleMapper.mapToRole(RoleReqDto.builder().build());
////            roleservice.saveRole(role1);
//
//
//
////            Role saisie = roleRepository.save(new Role(null,"Saisie"));
////            Role validation = roleRepository.save(new Role(null,"Validation"));
//            //Type
//            // TYPE AGENT
//            Type type00 = typeRepository.save(new Type(null,"TypeAgent","types agent",null));
//            Type type01 = typeRepository.save(new Type(null,"TypeAgent","Fonctionnaire",type00));
//            Type type02 = typeRepository.save(new Type(null,"TypeAgent","Stagiaire",type00));
//            //FONCTION
//            Type type10 = typeRepository.save(new Type(null,"Fonction","Fonctions",null));
//            Type type11 = typeRepository.save(new Type(null,"Fonction","Contractuel",type10));
//            Type type12 = typeRepository.save(new Type(null,"Fonction","Agent",type10));
//            Type type13 = typeRepository.save(new Type(null,"Fonction","Chargé d'études",type10));
//            //TYPE STRUCTURE
//            Type type20 = typeRepository.save(new Type(null,"STR","Structure",null));
//            Type type21 = typeRepository.save(new Type(null,"STR","Ministère",type20));
//            Type type22 = typeRepository.save(new Type(null,"STR","Direction Générale",type20));
//            Type type23 = typeRepository.save(new Type(null,"STR","Direction Centrale",type20));
//            Type type24 = typeRepository.save(new Type(null,"STR","Direction Centrale",type20));
//            Type type25 = typeRepository.save(new Type(null,"STR","Direction Régionale",type20));
//            Type type26 = typeRepository.save(new Type(null,"STR","Sous Direction",type20));
//            Type type27 = typeRepository.save(new Type(null,"STR","Service",type20));
//            Type type28 = typeRepository.save(new Type(null,"STR","Céllule",type20));
//
//            //MINISTERE
//            ///Structure stucture1=structureRepository.save(new Structure(null,"STR","STR","Structure",null,type20));
//            Structure stucture2=structureRepository.save(new Structure(null,"M001","MBPE","Ministère du budget et du portefeuille de l'Etat",null,type20));
//            Structure stucture3=structureRepository.save(new Structure(null,"MEF","MEF","Ministère de l'econnomie et des finances",null,type20));
//            //STRUCTURE
//
//            Structure stucture5=structureRepository.save(new Structure(null,"DGMP01","AA","Direction Generale des Marches publics",stucture2,type22));
//            Structure stucture6=structureRepository.save(new Structure(null,"DR0001","AA","Direction Régionale 1",stucture5,type23));
//            Structure stucture7=structureRepository.save(new Structure(null,"DR0002","AA","Direction Régionale 2",stucture5,type23));
//            Structure stucture8=structureRepository.save(new Structure(null,"SC0001","AA","Direction Centrale 1",stucture5,type24));
//            Structure stucture9=structureRepository.save(new Structure(null,"SD0001","AA","Sous Direction1",stucture6,type26));
//            Structure stucture10=structureRepository.save(new Structure(null,"SER001","AA","Service DGPM 1",stucture9,type27));
//            Structure stucture11=structureRepository.save(new Structure(null,"CEL001","AA","Céllule DGPM ",stucture10,type28));
//
//            //GRADES
//            Type type30 = typeRepository.save(new Type(null,"Grade","Grades",null));
//            Type type31 = typeRepository.save(new Type(null,"Grade","A7",type30));
//            Type type32 = typeRepository.save(new Type(null,"Grade","A6",type30));
//            Type type33 = typeRepository.save(new Type(null,"Grade","A5",type30));
//            Type type34 = typeRepository.save(new Type(null,"Grade","A4",type30));
//            Type type35 = typeRepository.save(new Type(null,"Grade","A3",type30));
//            Type type36 = typeRepository.save(new Type(null,"Grade","A2",type30));
//            Type type37 = typeRepository.save(new Type(null,"Grade","A1",type30));
//
//            Type type38 = typeRepository.save(new Type(null,"Grade","B3",type30));
//            Type type39 = typeRepository.save(new Type(null,"Grade","B2",type30));
//            Type type391 = typeRepository.save(new Type(null,"Grade","C1",type30));
//            Type type392 = typeRepository.save(new Type(null,"Grade","C2",type30));
//            Type type393 = typeRepository.save(new Type(null,"Grade","C3",type30));
//            Type type394 = typeRepository.save(new Type(null,"Grade","D1",type30));
//            Type type395 = typeRepository.save(new Type(null,"Grade","D2",type30));

//            Agent agent1=agentRepository.save(Agent.builder()
//                    .agtNom("SALIF").agtActif(true).agtAdresse("SALIF-ADRESS")
//                    .agtPrenom("YOUIN").agtDateNaissance(LocalDateTime.now()).agtMatricule("AGT001")
//                    .fonction(type11).typeAgt(type01)
//                    .structure(stucture5).agtTel("1445689458")
//                    .grade(type31).agtUserName("youin")
//                    .build());
//            Agent agent2=agentRepository.save(Agent.builder()
//                    .agtNom("ATSIN").agtActif(true).agtAdresse("ATSIN-ADRESS")
//                    .agtPrenom("GHISLAIN").agtDateNaissance(LocalDateTime.now()).agtMatricule("AGT002")
//                    .fonction(type12).typeAgt(type02)
//                    .structure(stucture5).agtTel("144568945258")
//                    .grade(type32).agtUserName("ATSIN")
//                    .build());
//            Agent agent3=agentRepository.save(Agent.builder()
//                    .agtNom("ESSOH").agtActif(true).agtAdresse("ESSOH-ADRESS")
//                    .agtPrenom("LOUES").agtDateNaissance(LocalDateTime.now()).agtMatricule("AGT003")
//                    .fonction(type11).typeAgt(type01)
//                    //.structure(stucture6)
//                    .agtTel("144568948758")
//                    .grade(type33).agtUserName("ESSOH")
//                    .build());
//            Agent agent4=agentRepository.save(Agent.builder()
//                    .agtNom("DJAN").agtActif(true).agtAdresse("DJAN-ADRESS")
//                    //.agtPrenom("OLIVIER")
//                    .agtDateNaissance(LocalDateTime.now()).agtMatricule("AGT004")
//                    .fonction(type13).typeAgt(type02)
//                    //.structure(stucture7)
//                    .agtTel("144745689458")
//                    .grade(type39).agtUserName("DJAN")
//                    .build());
//
//            //Demande
//            DemandeReqDto demandeReqDto = DemandeReqDto.builder()
//                    .demDateDebut(LocalDate.now())
//                    .demObjet("Congé annuel")
//                    .demDateSaisie(LocalDateTime.now())
//                    .demNbrJour(10)
//                    .demDateFin(LocalDate.of(2022, 07, 10).plusDays(10))
//                    .demAgtId(agent1.getAgtId()).build();
//
//            Demande demande1 = demandeMapper.mapToDemandeAuth(demandeReqDto);
//
//            /*Demande demande1 = DemandeAutorisation.builder()
//                    .demDateDebut(LocalDate.of(2022, 07, 10))
//                    .demNbrJour(10)
//                    .demDateFin(LocalDate.of(2022, 07, 10).plusDays(10))
//                    .build();
//            demande1.setAgent(agent1);
//            demande1.setDemDateSaisie(LocalDateTime.now());
//            demande1.setDemObjet("Test");*/
//            demandeRepository.save(demande1);
//
//
//
//
//            int numProcess=31;
//            //int numYear= Calendar.getInstance().get(Calendar.YEAR)%100;//Recuperer les deux dernueres années
//            int numYear= Calendar.getInstance().get(Calendar.YEAR);
//            String libelle=String.format("AGT-%02d-%03d",numYear,31);
//
//           // Date d = new Date();
//            //SimpleDateFormat form = new SimpleDateFormat("yy");
//            System.out.println(numProcess);
//            System.out.println(numYear);
//            System.out.println(libelle);
//           // System.out.println(form);
//           //String libelle= String.format("CC-%03d-AAAA",form.format(d),numProcess);
//            //System.out.println(libelle);
//
//            //List<String> selectConcat = agentRepository.selectConcat("DJA");
//            //List<Type> listedetail=typeRepository.findByType_TypIdOrderByTypIdDesc(1L);
//            //Page<Type> typeParIDparent=typeservice.getDetailsByParentId(1L,0,4);
//
//            //System.out.println(selectConcat);
//            //System.out.println("Liste detail "+listedetail);
//
//           List<Structure> listestructure=structureRepository.findStructureByTutelleDirecteIsNull();
//           System.out.println("Liste sructure parents "+listestructure);
//            //strService.getAllSousStructure(2L).forEach(System.out::println);
//
//            //System.out.println(strService.loadChildrenTree(stucture2));
//            Privilege prv = prvRepo.save(Privilege.builder().privilegeId(1L).privilegeName("Prvilege 1").build());
//            Role role = roleRepo.save(Role.builder().roleId(1L).roleName("Role 1").build());
//
//            ptrRepo.save(new PrivilegeToRole(prv, role));
//
//            System.out.println("==============================================");
//            //ptrRepo.findAll().forEach(System.out::println);
//           // System.out.println("=======================45454554=======================");
//            //ptrRepo.findByRole_RoleId(1L).forEach(System.out::println);
//           // System.out.println("=======================45454554=======================");
//            ptrRepo.findByRole_RoleIdOrderByAssIdDesc(1L).forEach(pr->System.out.println(pr.getPrivilege().getPrivilegeId()));
      };
   }

}
