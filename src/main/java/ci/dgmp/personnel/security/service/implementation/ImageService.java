package ci.dgmp.personnel.security.service.implementation;

import ci.dgmp.personnel.security.model.dao.UserImageRepository;
import ci.dgmp.personnel.security.model.entities.AppUser;
import ci.dgmp.personnel.security.model.entities.UserImage;
import ci.dgmp.personnel.security.service.constant.ImgConstants;
import ci.dgmp.personnel.security.service.interfac.ISecurityContextService;
import ci.dgmp.personnel.security.service.interfac.ImageIservice;
import ci.dgmp.personnel.service.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService implements ImageIservice {
    private final UserImageRepository imgRepo;
    private final ISecurityContextService scs;
    @Override
    public void upload(MultipartFile file, String chemin) {
        long fileSize = file.getSize();
        String fileExtension = getFileExtension(file);
        System.out.println("fileExtension = " + fileExtension);
        List<String> authorizedTypes = ImgConstants.DOCUMENT_AUTHORIZED_TYPE;

        if (fileSize > ImgConstants.UPLOAD_MAX_SIZE)
        {
            throw new AppException("Taille de fichier > " + (ImgConstants.UPLOAD_MAX_SIZE / 1000) + " Mo");
        }

        // Si l'extension du fichier n'est pas contenu dans la liste des types
        // authorisés
        if (!authorizedTypes.stream().anyMatch(type -> type.equalsIgnoreCase(fileExtension)))
        {
            throw new AppException("Type de fichier non pris en charge");
        }
        try
        {
            Files.write(Paths.get(chemin), file.getBytes());
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new AppException("Erreur de chargement du fichier");
        }
    }


    private String getFileExtension(MultipartFile file)
    {
        return file.getOriginalFilename().lastIndexOf(".") <0 ? "" : file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }

    private static String getFileExtension(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public byte[] download(String chemin) {
        File file = new File(chemin);
        Path path = Paths.get(file.toURI());
        try
        {
            return Files.readAllBytes(path);
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new AppException("Erreur de téléchargement");
        }
    }

    @Override
    public UserImage saveDoc(UserImage image) {
        UserImage img = new UserImage();
       // DocAgent docAgent = docMapper.mapToDocAgent(dto);
        this.upload(img.getFile(), img.getImgChemin() + getFileExtension(img.getFile()));
        img.setImgChemin(img.getImgChemin() + getFileExtension(img.getFile()));
        img = imgRepo.save(img);
        return img;
    }

    @Override
    public UserImage saveImage(UserImage image) {
        //UserImage image=new UserImage();
        AppUser user = scs.getAuthUser();
        image.setAppUser(user);
        image.setImgChemin(ImgConstants.uploadDir+"/photo_"+scs.getAuthUser().getUserId() + this.getFileExtension(image.getFile()));
        this.upload(image.getFile(), image.getImgChemin());
        if (imgRepo.existUserPhoto(user.getUserId())) image.setImgId(imgRepo.findImgId(user.getUserId()));
        return imgRepo.save(image);
    }
}
