package ci.dgmp.personnel;

import ci.dgmp.personnel.model.dao.*;
//import ci.dgmp.personnel.model.dto.DemandeReqDto;
import ci.dgmp.personnel.model.dto.StructureResDto;
import ci.dgmp.personnel.model.entities.*;
import ci.dgmp.personnel.security.model.dao.PrivilegeRepository;
import ci.dgmp.personnel.security.model.dao.PrivilegeToRoleRepository;
import ci.dgmp.personnel.security.model.dao.RoleRepository;
import ci.dgmp.personnel.security.model.dto.mapper.RoleMapper;
import ci.dgmp.personnel.security.model.dto.request.*;
import ci.dgmp.personnel.security.model.entities.Privilege;
import ci.dgmp.personnel.security.model.entities.PrivilegeToRole;
import ci.dgmp.personnel.security.model.entities.Role;
import ci.dgmp.personnel.security.model.entities.RoleToUser;
import ci.dgmp.personnel.security.service.interfac.*;
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


    @Bean
    CommandLineRunner start(RoleIservice roleservice, TypeRepository typeRepository, AgentRepository agentRepository,
                            TypeIservice typeservice, StructureRepository structureRepository, RoleMapper roleMapper, UserIservice userIservice,
                            StructureIservice strService, DemandeRepository demandeRepository, PrivilegeIservice pvService, PrivilegeToRoleIservice prService,
                            PrivilegeToRoleRepository ptrRepo, PrivilegeRepository prvRepo, RoleRepository roleRepo, UserIservice userService, RoleToUserIservice rtuService) {
        return (args) ->
        {
            //CERATION PRIVILEGE
            System.out.println("========================== CERATION DES PRIVILEGES======================");
            PrivilegeReqDto saisie = PrivilegeReqDto.builder().privilegeCode("AGT_SAISIE").privilegeName("Saisie agent").build();
            pvService.savePrivilege(saisie);
            PrivilegeReqDto modifier = PrivilegeReqDto.builder().privilegeCode("AGT_MODIFIER").privilegeName("Modifier agent").build();
            pvService.savePrivilege(modifier);
            PrivilegeReqDto supprimer = PrivilegeReqDto.builder().privilegeCode("AGT_SUPPRIMER").privilegeName("Supprimer agent").build();
            pvService.savePrivilege(supprimer);
            PrivilegeReqDto imprimer = PrivilegeReqDto.builder().privilegeCode("AGT_IMPRIMER").privilegeName("Imprimer fiche agent").build();
            pvService.savePrivilege(imprimer);
            PrivilegeReqDto imprimerListe = PrivilegeReqDto.builder().privilegeCode("AGT_IMPRIMER_LIST").privilegeName("Imprimer liste des agents").build();
            pvService.savePrivilege(imprimerListe);
            PrivilegeReqDto valider = PrivilegeReqDto.builder().privilegeCode("AGT_VALIDER").privilegeName("Valider agent").build();
            pvService.savePrivilege(valider);
            PrivilegeReqDto consulterListe = PrivilegeReqDto.builder().privilegeCode("AGT_CONSULTER_LISTE").privilegeName("Consulter liste agents").build();
            pvService.savePrivilege(consulterListe);
            PrivilegeReqDto consulterDetail = PrivilegeReqDto.builder().privilegeCode("AGT_CONSULTER_DEATIL").privilegeName("Consulter detail agent").build();
            pvService.savePrivilege(consulterDetail);
            PrivilegeReqDto rechercher = PrivilegeReqDto.builder().privilegeCode("AGT_RECHERCHE").privilegeName("Rechercher agent").build();
            pvService.savePrivilege(rechercher);
            //FIN PRIVILEGE

            //CREATION DE ROLE
            System.out.println("========================== CERATION DES ROLES======================");
            Role admin = roleRepo.save(new Role(1,"ADMIN","ADMIN",true,LocalDateTime.now(),null));
            Role users = roleRepo.save(new Role(2,"USER","USER",true,LocalDateTime.now(),null));
            Role dev = roleRepo.save(new Role(3,"DEV","DEV",true,LocalDateTime.now(),null));
            Role agent = roleRepo.save(new Role(4,"AGENT","AGENT",true,LocalDateTime.now(),null));
            //FIN CREARTION DE ROLE

            //ASSIGNATION D'UN ROLE A UN PRIVILEGE
            System.out.println("========================== ASSIGNATION D'UN ROLE A UN PRIVILEGE======================");
            PrivilegeToRoleReqDto pr1=PrivilegeToRoleReqDto.builder().privilegeId(1L).roleId(1L).build();
            prService.addPrivillegeToRole(pr1);
            PrivilegeToRoleReqDto pr2=PrivilegeToRoleReqDto.builder().privilegeId(2L).roleId(1L).build();
            prService.addPrivillegeToRole(pr2);
            PrivilegeToRoleReqDto pr3=PrivilegeToRoleReqDto.builder().privilegeId(3L).roleId(1L).build();
            prService.addPrivillegeToRole(pr3);
            PrivilegeToRoleReqDto pr4=PrivilegeToRoleReqDto.builder().privilegeId(4L).roleId(1L).build();
            prService.addPrivillegeToRole(pr4);
            PrivilegeToRoleReqDto pr5=PrivilegeToRoleReqDto.builder().privilegeId(5L).roleId(1L).build();
            prService.addPrivillegeToRole(pr5);
            PrivilegeToRoleReqDto pr6=PrivilegeToRoleReqDto.builder().privilegeId(6L).roleId(1L).build();
            prService.addPrivillegeToRole(pr6);
            PrivilegeToRoleReqDto pr7=PrivilegeToRoleReqDto.builder().privilegeId(7L).roleId(1L).build();
            prService.addPrivillegeToRole(pr7);
            PrivilegeToRoleReqDto pr8=PrivilegeToRoleReqDto.builder().privilegeId(8L).roleId(1L).build();
            prService.addPrivillegeToRole(pr8);
            PrivilegeToRoleReqDto pr9=PrivilegeToRoleReqDto.builder().privilegeId(9L).roleId(1L).build();
            prService.addPrivillegeToRole(pr9);
            //FIN ASSIGNATION D'UN ROLE A UN PRIVILEGE

            //CREATION D'UN UTILISATEUR
            System.out.println("========================== CREATION D'UN UTILISATEUR======================");
            UserReqDto user1=UserReqDto.builder()
                    .userLogin("salif")
                    .userPassword("lovalova")
                    .userConfirmPassword("lovalova")
                    .userNom("YOUIN")
                    .userPrenom("Salif")
                    .userEmail("salifyouin@gmail.com")
                    .userTelephone("0758587190")
                    .build();
            userIservice.saveUser(user1);
            //FIN CREATION D'UN UTILISATEUR

            // TYPE AGENT
            Type type00 = typeRepository.save(new Type(null,"TypeAgent","types agent",null,0L));
            Type type01 = typeRepository.save(new Type(null,"TypeAgent","Fonctionnaire",type00,1L));
            Type type02 = typeRepository.save(new Type(null,"TypeAgent","Stagiaire",type00,1L));
            //FONCTION
            Type type10 = typeRepository.save(new Type(null,"Fonction","Fonctions",null,0L));
            Type type11 = typeRepository.save(new Type(null,"Fonction","Contractuel",type10,1L));
            Type type12 = typeRepository.save(new Type(null,"Fonction","Agent",type10,1L));
            Type type13 = typeRepository.save(new Type(null,"Fonction","Chargé d'études",type10,1L));
            //TYPE STRUCTURE
            Type type20 = typeRepository.save(new Type(null,"STR","Structure",null,0L));
            Type type21 = typeRepository.save(new Type(null,"MIN","Ministère",type20,1L));
            Type type22 = typeRepository.save(new Type(null,"DG","Direction Générale",type20,2L));
            Type type23 = typeRepository.save(new Type(null,"DC","Direction Centrale",type20,3L));
            Type type25 = typeRepository.save(new Type(null,"DR","Direction Régionale",type20,4L));
            Type type26 = typeRepository.save(new Type(null,"SD","Sous Direction",type20,5L));
            Type type27 = typeRepository.save(new Type(null,"SCE","Service",type20,6L));
            Type type28 = typeRepository.save(new Type(null,"CEL","Céllule",type20,7L));

            //MINISTERE
            ///Structure stucture1=structureRepository.save(new Structure(null,"STR","STR","Structure",null,type20));
           Structure stucture2=structureRepository.save(new Structure(null,"M001","MBPE","Ministère du budget et du portefeuille de l'Etat",null,type20));
            Structure stucture3=structureRepository.save(new Structure(null,"MEF","MEF","Ministère de l'econnomie et des finances",null,type20));
            //STRUCTURE

            Structure stucture5=structureRepository.save(new Structure(null,"DGMP01","AA","Direction Generale des Marches publics",stucture2,type22));
            Structure stucture6=structureRepository.save(new Structure(null,"DR0001","AA","Direction Régionale 1",stucture5,type23));
            Structure stucture7=structureRepository.save(new Structure(null,"DR0002","AA","Direction Régionale 2",stucture5,type23));
            Structure stucture8=structureRepository.save(new Structure(null,"SC0001","AA","Direction Centrale 1",stucture5,type23));
            Structure stucture9=structureRepository.save(new Structure(null,"SD0001","AA","Sous Direction1",stucture6,type26));
            Structure stucture10=structureRepository.save(new Structure(null,"SER001","AA","Service DGPM 1",stucture9,type27));
            Structure stucture11=structureRepository.save(new Structure(null,"CEL001","AA","Céllule DGPM ",stucture10,type28));

            //GRADES
            Type type30 = typeRepository.save(new Type(null,"Grade","Grades",null,0L));
            Type type31 = typeRepository.save(new Type(null,"Grade","A7",type30,1L));
            Type type32 = typeRepository.save(new Type(null,"Grade","A6",type30,2L));
            Type type33 = typeRepository.save(new Type(null,"Grade","A5",type30,3L));
            Type type34 = typeRepository.save(new Type(null,"Grade","A4",type30,4L));
            Type type35 = typeRepository.save(new Type(null,"Grade","A3",type30,5L));
            Type type36 = typeRepository.save(new Type(null,"Grade","A2",type30,6L));
            Type type37 = typeRepository.save(new Type(null,"Grade","A1",type30,7L));

            Type type38 = typeRepository.save(new Type(null,"Grade","B3",type30,8L));
            Type type39 = typeRepository.save(new Type(null,"Grade","B2",type30,9L));
            Type type391 = typeRepository.save(new Type(null,"Grade","C1",type30,12L));
            Type type392 = typeRepository.save(new Type(null,"Grade","C2",type30,11L));
            Type type393 = typeRepository.save(new Type(null,"Grade","C3",type30,10L));
            Type type394 = typeRepository.save(new Type(null,"Grade","D1",type30,14L));
            Type type395 = typeRepository.save(new Type(null,"Grade","D2",type30,13L));

            //ASSIGNATION D'UN ROLE A UN UTILISATEUR
            System.out.println("========================== ASSIGNATION D'UN ROLE A UN UTILISATEUR======================");
            RoleToUserReqDto rtu1=RoleToUserReqDto.builder()
                    .userId(1L)
                    .roleId(1L)
                    .assStrId(3L)
                    .build();
            rtuService.addRoleToUser(rtu1);
            // FIN ASSIGNATION D'UN ROLE A UN UTILISATEUR

              /* Agent agent1=agentRepository.save(Agent.builder()
                    .agtNom("SALIF").agtActif(true).agtAdresse("SALIF-ADRESS")
                    .agtPrenom("YOUIN").agtDateNaissance(LocalDate.now()).agtMatricule("AGT001")
                    .fonction(type11).typeAgt(type01)
                    .structure(stucture5).agtTel("1445689458")
                    .grade(type31).agtUserName("youin")
                    .build());
            Agent agent2=agentRepository.save(Agent.builder()
                    .agtNom("ATSIN").agtActif(true).agtAdresse("ATSIN-ADRESS")
                    .agtPrenom("GHISLAIN").agtDateNaissance(LocalDate.now()).agtMatricule("AGT002")
                    .fonction(type12).typeAgt(type02)
                    .structure(stucture5).agtTel("144568945258")
                    .grade(type32).agtUserName("ATSIN")
                    .build());
            Agent agent3=agentRepository.save(Agent.builder()
                    .agtNom("ESSOH").agtActif(true).agtAdresse("ESSOH-ADRESS")
                    .agtPrenom("LOUES").agtDateNaissance(LocalDate.now()).agtMatricule("AGT003")
                    .fonction(type11).typeAgt(type01)
                    //.structure(stucture6)
                    .agtTel("144568948758")
                    .grade(type33).agtUserName("ESSOH")
                    .build());
            Agent agent4=agentRepository.save(Agent.builder()
                    .agtNom("DJAN").agtActif(true).agtAdresse("DJAN-ADRESS")
                    //.agtPrenom("OLIVIER")
                    .agtDateNaissance(LocalDate.now()).agtMatricule("AGT004")
                    .fonction(type13).typeAgt(type02)
                    //.structure(stucture7)
                    .agtTel("144745689458")
                    .grade(type39).agtUserName("DJAN")
                    .build());*/

//            //Demande
//            DemandeReqDto demandeReqDto = DemandeReqDto.builder()
//                    .demDateDebut(LocalDate.now())
//                    .demObjet("Congé annuel")
//                    .demDateSaisie(LocalDateTime.now())
//                    .demNbrJour(10)
//                    .demDateFin(LocalDate.of(2022, 07, 10).plusDays(10))
//                    .demAgtId(agent1.getAgtId()).build();

            // Demande demande1 = demandeMapper.mapToDemandeAuth(demandeReqDto);

            /*Demande demande1 = DemandeAutorisation.builder()
                    .demDateDebut(LocalDate.of(2022, 07, 10))
                    .demNbrJour(10)
                    .demDateFin(LocalDate.of(2022, 07, 10).plusDays(10))
                    .build();
            demande1.setAgent(agent1);
            demande1.setDemDateSaisie(LocalDateTime.now());
            demande1.setDemObjet("Test");*/
            // demandeRepository.save(demande1);


       /*     int numProcess=31;
            //int numYear= Calendar.getInstance().get(Calendar.YEAR)%100;//Recuperer les deux dernueres années
            int numYear= Calendar.getInstance().get(Calendar.YEAR);
            String libelle=String.format("AGT-%02d-%03d",numYear,31);

           // Date d = new Date();
            //SimpleDateFormat form = new SimpleDateFormat("yy");
            System.out.println(numProcess);
            System.out.println(numYear);
            System.out.println(libelle);*/
           // System.out.println(form);
           //String libelle= String.format("CC-%03d-AAAA",form.format(d),numProcess);
            //System.out.println(libelle);

            //List<String> selectConcat = agentRepository.selectConcat("DJA");
            //List<Type> listedetail=typeRepository.findByType_TypIdOrderByTypIdDesc(1L);
            //Page<Type> typeParIDparent=typeservice.getDetailsByParentId(1L,0,4);

            //System.out.println(selectConcat);
            //System.out.println("Liste detail "+listedetail);

           //List<Structure> listestructure=structureRepository.findStructureByTutelleDirecteIsNull();
           //System.out.println("Liste sructure parents "+listestructure);
            //strService.getAllSousStructure(2L).forEach(System.out::println);

            //System.out.println(strService.loadChildrenTree(stucture2));
            //Privilege prv = prvRepo.save(Privilege.builder().privilegeId(1L).privilegeName("Prvilege 1").build());
            //Role role = roleRepo.save(Role.builder().roleId(1L).roleName("Role 1").build());

            //ptrRepo.save(new PrivilegeToRole(prv, role));

            //System.out.println("==============================================");
            //ptrRepo.findAll().forEach(System.out::println);
           // System.out.println("=======================45454554=======================");
            //ptrRepo.findByRole_RoleId(1L).forEach(System.out::println);
           // System.out.println("=======================45454554=======================");
            //ptrRepo.findByRole_RoleIdOrderByAssIdDesc(1L).forEach(pr->System.out.println(pr.getPrivilege().getPrivilegeId()));
      };
   }

}
