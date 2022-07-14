package ci.dgmp.personnel.security.service.interfac;

import ci.dgmp.personnel.security.model.entities.UserImage;
import org.springframework.web.multipart.MultipartFile;

public interface ImageIservice {
    void upload(MultipartFile file, String chemin);
    byte[] download(String chemin);
    UserImage saveDoc(UserImage image);
    UserImage saveImage(UserImage image);
}
