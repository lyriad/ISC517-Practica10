package com.web.pucmm.practica10.Controllers;

import com.web.pucmm.practica10.Services.InvoiceService;
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

    @GetMapping
    public String list( Model model ) {

        model.addAttribute("invoices", invoiceService.all());

        return "/freemarker/invoices/list";
    }

    @GetMapping("/register")
    public String register( Model model ) {

        return "/freemarker/invoices/register";
    }
} 