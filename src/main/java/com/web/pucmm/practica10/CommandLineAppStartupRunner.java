package com.web.pucmm.practica10;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import com.web.pucmm.practica10.Models.Role;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Repositories.RoleRepository;
import com.web.pucmm.practica10.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String...args) throws Exception {
        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        roleRepository.save(new Role("EMPLOYEE"));
        roleRepository.save(new Role("CLIENT"));

        userRepository.save(new User(
            "00000000000",
            "Admin",
            "admin@example.com",
            "Admin",
            "000-000-0000",
            "Street",
            passwordEncoder.encode("password"),
            true,
            new HashSet<>(Arrays.asList(adminRole)),
            null
        ));
    }
}