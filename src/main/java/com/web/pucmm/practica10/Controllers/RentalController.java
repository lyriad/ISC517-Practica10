package com.web.pucmm.practica10.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Models.Rental;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.EquipmentService;
import com.web.pucmm.practica10.Services.RentalService;
import com.web.pucmm.practica10.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    UserService clientService;
    
    @PostMapping("/register")
    public String postRegister(@RequestParam(name = "id_equipment") long id_equipment, @RequestParam(name = "clientIdNumber") String clientIdNumber, @RequestParam(name = "amount") int amount, @RequestParam(name = "createdAt") String createdAt, @RequestParam(name = "returnDate") String returnDate) {

        Equipment equipment = equipmentService.findById(id_equipment);
        if (equipment == null) return "redirect:/error";

        User client = clientService.findByIdNumber(clientIdNumber);
        if (client == null) return "redirect:/error";

        Date createdAtDate, promisedReturnDate;
        try {
            createdAtDate = new SimpleDateFormat("yyyy-MM-dd").parse(createdAt);
            promisedReturnDate = new SimpleDateFormat("yyyy-MM-dd").parse(returnDate);
        } catch (ParseException e) {
            return "redirect:/error";
        }

        Rental rental = new Rental(equipment, client, amount, createdAtDate, promisedReturnDate);

        rentalService.create(rental);
        return String.format("redirect:/clients/%s", clientIdNumber);
    }
}