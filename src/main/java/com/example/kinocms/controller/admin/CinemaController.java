package com.example.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CinemaController {
    @GetMapping("/admin/cinemas")
    public String cinemas(){
        return "/admin/cinemas";
    }
}
