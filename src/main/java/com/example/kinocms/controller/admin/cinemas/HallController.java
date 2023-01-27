package com.example.kinocms.controller.admin.cinemas;

import com.example.kinocms.entity.cinemas.Hall;
import com.example.kinocms.entity.cinemas.PicturesHall;
import com.example.kinocms.repos.HallRepository;
import com.example.kinocms.service.CinemaService;
import com.example.kinocms.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;


@Controller
public class HallController {
    @Value("${upload.path:${user.home}}")
    private String uploadDir;
    @Autowired
    private HallRepository hallRepository;


    @GetMapping("/admin/hall.{id}")
    public String details_film(@PathVariable("id") int id, Model model){
        if(!HallService.isChange()){
            if(id != 0){
                HallService.setHall(hallRepository.findById(id).get());
            }
            else{
                HallService.setHall(new Hall());
            }
        }
        model.addAttribute("hall", HallService.getHall());
        return "/admin/hall";
    }


    @PostMapping(value = "/admin/hall.{id}", params = "addSchemeHall")
    public void uploadSсhemeHall(@RequestParam(value = "fileSchemeHall", required = false) MultipartFile file,
                                  @ModelAttribute Hall hall) {

        setValuesHall(hall);
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            hall.setSchemeHall("../img/"+copyLocation.getFileName());
            HallService.getHall().setSchemeHall(hall.getSchemeHall());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/admin/hall.{id}", params = "addTopBanner")
    public void uploadTopBanner(@RequestParam(value = "fileTopBanner", required = false) MultipartFile file,
                                 @ModelAttribute Hall hall) {

        setValuesHall(hall);
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            hall.setTopBanner("../img/"+copyLocation.getFileName());
            HallService.getHall().setTopBanner(hall.getTopBanner());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/admin/hall.{id}", params = "add")
    public void uploadPictures(@RequestParam(value = "files", required = false) MultipartFile[] files,
                               @ModelAttribute Hall hall) {

        setValuesHall(hall);

        for(MultipartFile file : files){
            try {
                Path copyLocation = Paths
                        .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                hall.addPicture(new PicturesHall("../img/"+copyLocation.getFileName()));
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "/admin/hall.{id}", params = "deleteImage")
    public void deleteImage(@ModelAttribute Hall hall,
                            @RequestParam(name = "deleteImage", required = false) int deleteImage){
        setValuesHall(hall);
        hall.deletePicture(deleteImage);
    }


    @PostMapping(value = "/admin/hall.{id}", params = "save")
    public String save(@ModelAttribute Hall hall, @PathVariable int id){
        setValuesHall(hall);
        hall.setCreateDate(LocalDate.now());
        CinemaService.setCreateHall(true);
        if(!CinemaService.getCinema().getDetailsCinema().getListHall().isEmpty() && HallService.isChange()){
            CinemaService.getCinema().getDetailsCinema().getListHall().remove(id);
        }
        CinemaService.getCinema().getDetailsCinema().getListHall().add(id, hall);
        HallService.setChange(false);

        return "redirect:/admin/details_cinema." + CinemaService.getCinema().getCinemaID();
    }

    public void setValuesHall(Hall hall){
        hall.setListPicture(HallService.getHall().getListPicture());
        hall.setSchemeHall(HallService.getHall().getSchemeHall());
        hall.setTopBanner(HallService.getHall().getTopBanner());
    }
}