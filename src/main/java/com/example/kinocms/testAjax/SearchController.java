package com.example.kinocms.testAjax;

import com.example.kinocms.entity.film.PicturesFilm;
import com.example.kinocms.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
public class SearchController {

    UserService userService;

    @Value("${upload.path: ${user.home}}")
    private String uploadDir;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*@PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }*/

    @PostMapping("/upload-main-picture")
    @ResponseBody
    public ResponseEntity<String> uploadMainPicture(MultipartFile fileMainPicture) {
        System.out.println("upload main picture");
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(fileMainPicture.getOriginalFilename()));

            FilmService.getFilm().setMainPicture("../img/"+copyLocation.getFileName());
            Files.copy(fileMainPicture.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(FilmService.getFilm());

        return ResponseEntity.ok(FilmService.getFilm().getMainPicture());
    }

    @PostMapping("/upload-picture-gallery")
    @ResponseBody
    public ResponseEntity<List<PicturesFilm>> uploadPictureGallery(MultipartFile[] pictureGallery, Model model) {
        System.out.println("Picture Gallery");
        for(MultipartFile picture : pictureGallery){
            try {
                Path copyLocation = Paths
                        .get(uploadDir + File.separator + StringUtils.cleanPath(picture.getOriginalFilename()));

                FilmService.getFilm().addPicture(new PicturesFilm("../img/"+copyLocation.getFileName()));

                Files.copy(picture.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<PicturesFilm> picture = FilmService.getFilm().getListPicture();
        FilmService.getFilm().getListPicture().forEach(
                x -> System.out.println(x.getPicture())
        );

        model.addAttribute("film", FilmService.getFilm());

        return ResponseEntity.ok(picture);
    }

    @PostMapping("/file-upload")
    @ResponseBody
    public ResponseEntity<String> fileUpload(MultipartFile file) {

        String result = null;

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            result = "../img/"+copyLocation.getFileName();
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return ResponseEntity.ok(result);
    }

}
//更多请阅读：https://www.yiibai.com/spring-boot/ajax-example.html


