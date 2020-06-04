package com.web.pucmm.practica10.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getLogin(Model model) {

        return "/freemarker/auth/login";
    }
}