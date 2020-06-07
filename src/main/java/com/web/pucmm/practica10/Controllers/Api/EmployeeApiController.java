package com.web.pucmm.practica10.Controllers.Api;

import com.web.pucmm.practica10.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApiController {
    
    @Autowired
    UserService userService;

    @GetMapping("/{id_employee}/invoices/pending")
    public int getCountPendingInvoices(@PathVariable long id_employee) {
        return userService.getCountPendingInvoices(id_employee);
    }
}