package com.example.kinocms.controller;

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
public class DetailsFilmController {
    @Value("${upload.path:${user.home}}")
    private String uploadDir;
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/details_film.{id}")
    public String details_film(@PathVariable("id") int id, Model model){
        if(id != 0){
            FilmService.setFilm(filmRepository.findById(id).get());
        }
        else{
            FilmService.setFilm(new Film());
        }
        model.addAttribute("film", FilmService.getFilm());
        return "details_film";
    }

    @PostMapping(value = "/details_film.{id}", params = "addMainPicture")
    public String uploadMainPicture(@RequestParam(value = "file", required = false) MultipartFile file,
                                    @ModelAttribute Film film) {

        film.setListPicture(FilmService.getFilm().getListPicture());
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

            film.setMainPicture("../img/"+copyLocation.getFileName());
            FilmService.setFilm(film);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "details_film";
    }

    @PostMapping(value = "/details_film.{id}", params = "add")
    public String uploadPictures(@RequestParam(value = "files", required = false) MultipartFile[] files,
                                    @ModelAttribute Film film) {

        film.setMainPicture(FilmService.getFilm().getMainPicture());

        for(MultipartFile file : files){
            try {
                Path copyLocation = Paths
                        .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                film.addPicture(new PicturesFilm("../img/"+copyLocation.getFileName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        FilmService.setFilm(film);
        System.out.println(film);
        return "details_film";
    }


    @PostMapping(value = "/details_film.{id}", params = "save")
    public String save(@ModelAttribute Film film){
        film.setFilmID(FilmService.getFilm().getFilmID());
        film.setMainPicture(FilmService.getFilm().getMainPicture());
        film.setListPicture(FilmService.getFilm().getListPicture());
        film.getDetailsFilm().setDetailsFilmID(FilmService.getFilm().getDetailsFilm().getDetailsFilmID());

        filmRepository.save(film);
        return "redirect:/films";
    }

    @GetMapping("/advanced")
    public String advanced(){
        return "advanced";
    }

}
