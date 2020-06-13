package com.web.pucmm.practica10.Controllers.Api;

import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clients")
public class ClientApiController {

    @Autowired
    UserService clientService;

    @GetMapping("/search/{param}")
    public User search(@PathVariable("param") String param) {
        
        User client = clientService.findByIdNumber(param);

        if (client == null) clientService.findByEmail(param);
        if (client == null) throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Client not found" );

        return client;
    }
}