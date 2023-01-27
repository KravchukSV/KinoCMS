package com.example.kinocms.controller.admin.cinemas;

import com.example.kinocms.repos.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CinemaController {
    @Autowired
    CinemaRepository cinemaRepository;

    @GetMapping("/admin/cinemas")
    public String cinemas(Model model){
        model.addAttribute("cinemas", cinemaRepository.findAll());
        return "/admin/cinemas";
    }
}
