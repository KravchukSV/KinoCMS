package com.example.kinocms.controller;

import com.example.kinocms.entity.film.Film;
import com.example.kinocms.repos.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/films")
    public String films(Model model){
        Iterable<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "films";
    }

    @PostMapping(value = "/film", params = "details")
    public String details(@ModelAttribute Film film){
        return "redirect:details_film";
    }
}
