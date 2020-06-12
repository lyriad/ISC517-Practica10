package com.web.pucmm.practica10.Controllers.Api;

import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentApiController {
    
    @Autowired
    EquipmentService service;

    @GetMapping("/search/{name}")
    public Equipment getSubcategories(@PathVariable String name) {

        Equipment equipment = service.searchByName(name);
        if ( equipment == null )
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Equipment not found" );

        return equipment;
    }
}