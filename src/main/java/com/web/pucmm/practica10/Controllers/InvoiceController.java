package com.web.pucmm.practica10.Controllers;

import java.security.Principal;

import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.InvoiceService;
import com.web.pucmm.practica10.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    UserService userService;

    @GetMapping
    public String list( Principal principal, Model model ) {

        User user = userService.getLoggedUser(principal);
        model.addAttribute("invoices", invoiceService.all());
        model.addAttribute("auth", user);

        return "/freemarker/invoices/list";
    }

    @GetMapping("/register")
    public String register( Model model ) {

        return "/freemarker/invoices/register";
    }
} 