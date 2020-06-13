package com.web.pucmm.practica10.Controllers;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Models.Invoice;
import com.web.pucmm.practica10.Models.Rental;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.EquipmentService;
import com.web.pucmm.practica10.Services.InvoiceService;
import com.web.pucmm.practica10.Services.RentalService;
import com.web.pucmm.practica10.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    UserService userService;

    @Autowired
    RentalService rentalService;

    @Autowired
    EquipmentService equipmentService;

    @GetMapping
    public String list(Principal principal, Model model) {

        User user = userService.getLoggedUser(principal);
        model.addAttribute("invoices", invoiceService.all());
        model.addAttribute("auth", user);

        return "/freemarker/invoices/list";
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal) {

        User user = userService.getLoggedUser(principal);

        model.addAttribute("auth", user);

        return "/freemarker/invoices/register";
    }

    @PostMapping("/register")
    @Transactional
    public String postRegister(Principal principal, RedirectAttributes attrs,
            @RequestParam(name = "rentals") long[] rentals, @RequestParam(name = "id_client") long id_client) {

        User client = userService.findById(id_client);
        List<Rental> myRentals = new ArrayList<>();
        User employee = userService.getLoggedUser(principal);

        float total = 0;

        for (int i = 0; i < rentals.length; i++) {
            Rental rental = rentalService.findById(rentals[i]);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            Date date = new Date();
            try {
                date = format.parse(dateString);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            rental.setRealReturnDate(date);
            rentalService.update(rental);
            
            Equipment equipment = rental.getEquipment();
            equipment.setCantAvailable(equipment.getCantAvailable() + rental.getAmount());
            equipmentService.update(equipment);
            myRentals.add(rental);
            total += rental.getCost();
        }

        Invoice invoice = new Invoice(myRentals, client, employee, total, true );

        invoiceService.create(invoice);
         
        return "redirect:/invoices";
    }
} 