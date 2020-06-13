package com.web.pucmm.practica10.Controllers.Api;

import java.util.List;

import com.web.pucmm.practica10.Models.Rental;
import com.web.pucmm.practica10.Services.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class RentalApiController {
    
    @Autowired
    RentalService rentalService;

    @GetMapping("/client/{idNumber}/pending")
    public List<Rental> getPendingByClient(@PathVariable String idNumber) {
        
        return rentalService.getPendingByClient(idNumber);
    }
}