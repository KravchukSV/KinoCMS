package com.example.kinocms.controller.admin.films;

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

    @GetMapping("/admin/films")
    public String films(Model model){
        model.addAttribute("films", filmRepository.findAll());
        return "/admin/films";
    }

    @PostMapping(value = "/admin/films", params = "details")
    public String details(@ModelAttribute Film film){
        return "redirect:/admin/details_film";
    }
}
