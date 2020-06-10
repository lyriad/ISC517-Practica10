package com.web.pucmm.practica10.Controllers;

import java.util.Locale;
import com.web.pucmm.practica10.Services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    RentalService rentalService;
    
    @GetMapping
    public String list( Model model ) {

        model.addAttribute("rentals", rentalService.getPendingOrdered());

        return "/freemarker/rentals/list";
    }

}