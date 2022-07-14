package ci.dgmp.personnel.security.model.dao;

import ci.dgmp.personnel.security.model.entities.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    @Query("select u from UserImage u where u.appUser.userId = :userId")
    UserImage getPhotoPath(@Param("userId") Long userId);

    @Query("select (count(u) > 0) from UserImage u where u.appUser.userId = ?1")
    boolean existUserPhoto(Long userId);

    @Query("select img.imgChemin from UserImage img where img.appUser.userId = ?1")
    String findImgPath(Long userId);

    @Query("select img.imgId from UserImage img where img.appUser.userId = ?1")
    Long findImgId(Long userId);

    @Query("select img from UserImage img where img.appUser.userId = ?1")
    UserImage findUserImg(Long userId);

}
