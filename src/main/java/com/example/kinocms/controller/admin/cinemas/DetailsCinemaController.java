package com.example.kinocms.controller.admin.cinemas;

import com.example.kinocms.entity.cinemas.Cinema;
import com.example.kinocms.entity.cinemas.PicturesCinema;
import com.example.kinocms.repos.CinemaRepository;
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

@Controller
public class DetailsCinemaController {
    @Value("${upload.path: ${user.home}}")
    private String uploadDir;
    @Autowired
    private CinemaRepository cinemaRepository;


    @GetMapping("/admin/details_cinema.{id}")
    public String details_cinema(@PathVariable("id") int id, Model model){
        if(!CinemaService.isCreateHall()){
            if(id != 0){
                CinemaService.setCinema(cinemaRepository.findById(id).get());
            }
            else{
                CinemaService.setCinema(new Cinema());
            }
            CinemaService.setCreateHall(false);
        }

        model.addAttribute("cinema", CinemaService.getCinema());
        return "/admin/details_cinema";
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "addLogotype")
    public void uploadLogotype(@RequestParam(value = "fileLogotype", required = false) MultipartFile file,
                                  @ModelAttribute Cinema cinema) {
        setValuesCinema(cinema);
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            cinema.setLogotype("../img/"+copyLocation.getFileName());
            CinemaService.getCinema().setLogotype(cinema.getLogotype());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "addTopBanner")
    public void uploadTopBanner(@RequestParam(value = "fileTopBanner", required = false) MultipartFile file,
                               @ModelAttribute Cinema cinema) {

        setValuesCinema(cinema);
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            cinema.getDetailsCinema().setTopBanner("../img/"+copyLocation.getFileName());
            CinemaService.getCinema().getDetailsCinema().setTopBanner(cinema.getDetailsCinema().getTopBanner());
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "add")
    public void uploadPictures(@RequestParam(value = "files", required = false) MultipartFile[] files,
                               @ModelAttribute Cinema cinema) {

        setValuesCinema(cinema);

        for(MultipartFile file : files){
            try {
                Path copyLocation = Paths
                        .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                cinema.addPicture(new PicturesCinema("../img/"+copyLocation.getFileName()));
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "deleteImage")
    public void deleteImage(@ModelAttribute Cinema cinema,
                            @RequestParam(name = "deleteImage", required = false) int deleteImage){
        setValuesCinema(cinema);
        cinema.deletePicture(deleteImage);
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "deleteHall")
    public void deleteHall(@ModelAttribute Cinema cinema,
                            @RequestParam(name = "deleteHall", required = false) int deleteHall){
        setValuesCinema(cinema);
        CinemaService.getCinema().getDetailsCinema().deleteHall(deleteHall);
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "changeHall")
    public String changeHall(@ModelAttribute Cinema cinema,
                           @RequestParam(name = "changeHall", required = false) int changeHall){
        setValuesCinema(cinema);
        HallService.setHall(CinemaService.getCinema().getDetailsCinema().getHall(changeHall));
        HallService.setChange(true);
        return "redirect:/admin/hall." + changeHall;
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "createHall")
    public String createHall(@ModelAttribute Cinema cinema){
        return "redirect:/admin/hall.0";
    }

    @PostMapping(value = "/admin/details_cinema.{id}", params = "save")
    public String save(@ModelAttribute Cinema cinema){
        setValuesCinema(cinema);
        System.out.println(CinemaService.getCinema());
        cinemaRepository.save(CinemaService.getCinema());
        return "redirect:/admin/cinemas";
    }

    public void setValuesCinema(Cinema cinema){
        CinemaService.getCinema().setName(cinema.getName());
        CinemaService.getCinema().getDetailsCinema().setDescription(cinema.getDetailsCinema().getDescription());
        CinemaService.getCinema().getDetailsCinema().setConditions(cinema.getDetailsCinema().getConditions());
        CinemaService.getCinema().setSeo(cinema.getSeo());

        cinema.getDetailsCinema().setTopBanner(CinemaService.getCinema().getDetailsCinema().getTopBanner());
        cinema.setListPicture(CinemaService.getCinema().getListPicture());
        cinema.setLogotype(CinemaService.getCinema().getLogotype());
        cinema.getDetailsCinema().setListHall(CinemaService.getCinema().getDetailsCinema().getListHall());
    }
}