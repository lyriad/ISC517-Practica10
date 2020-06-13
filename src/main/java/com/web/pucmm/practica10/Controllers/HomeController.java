package com.web.pucmm.practica10.Controllers;

import java.security.Principal;

import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index( Principal principal, Model model ) {

        User user = userService.getLoggedUser(principal);
        model.addAttribute("auth", user);

        return "/freemarker/dashboard";
    }
}