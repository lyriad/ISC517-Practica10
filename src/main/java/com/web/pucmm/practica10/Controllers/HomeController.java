package com.web.pucmm.practica10.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String index( Model model ) {

        return "/freemarker/dashboard";
    }
}