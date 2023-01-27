package com.example.kinocms.controller.admin;

import com.example.kinocms.entity.film.Film;
import com.example.kinocms.entity.film.PicturesFilm;
import com.example.kinocms.repos.FilmRepository;
import com.example.kinocms.service.FilmService;
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
public class GreetingController {

    @Value("${upload.path:${user.home}}")
    private String uploadDir;
    @Autowired
    private FilmRepository filmRepository;

    /*@GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                           String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }*/

    @GetMapping("/ajax.{id}")
    public String ajax(@PathVariable("id") int id, Model model){
        if(id != 0){
            FilmService.setFilm(filmRepository.findById(id).get());
        }
        else{
            FilmService.setFilm(new Film());
        }
        //FilmService.getFilm().addPicture(new PicturesFilm("../img/logo.png"));
        //FilmService.getFilm().addPicture(new PicturesFilm("../img/logo.png"));
        //FilmService.getFilm().addPicture(new PicturesFilm("../img/logo.png"));

        System.out.println();
        model.addAttribute("film", FilmService.getFilm());
        return "/ajax";
    }

    @PostMapping(value = "/ajax.{id}", params = "add")
    public void uploadPictures(@RequestParam(value = "files", required = false) MultipartFile[] files,
                               @ModelAttribute Film film) {

        setValuesFilm(film);

        for(MultipartFile file : files){
            try {
                Path copyLocation = Paths
                        .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
                film.addPicture(new PicturesFilm("../img/"+copyLocation.getFileName()));
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "/ajax.{id}", params = "deleteImage")
    public void deleteImage(@ModelAttribute Film film,
                            @RequestParam(name = "deleteImage", required = false) int deleteImage){
        System.out.println("Delete Image");
        setValuesFilm(film);
        film.deletePicture(deleteImage);
    }


    @PostMapping(value = "/ajax.{id}", params = "save")
    public void save(@ModelAttribute Film film){

        setValuesFilm(film);
        filmRepository.save(FilmService.getFilm());
        System.out.println("Save");
        System.out.println(FilmService.getFilm());
    }

    public void setValuesFilm(Film film){
        FilmService.getFilm().setName(film.getName());
        FilmService.getFilm().setDetailsFilm(film.getDetailsFilm());
        FilmService.getFilm().setSeo(film.getSeo());
        film.setListPicture(FilmService.getFilm().getListPicture());
        film.setMainPicture(FilmService.getFilm().getMainPicture());
    }
}
