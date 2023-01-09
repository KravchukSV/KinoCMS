package com.example.kinocms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    /*@GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                           String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }*/

    @GetMapping("/admin/admin")
    public String admin(){
        return "/admin/admin";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
