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

        Role adminRole = roleRepository.findByRole("ADMIN");
        if ( adminRole == null ) {
            adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
        }

        Role clientRole = roleRepository.findByRole("CLIENT");
        if ( clientRole == null ) {
            clientRole = new Role("CLIENT");
            roleRepository.save(clientRole);
        }

        Role employeeRole = roleRepository.findByRole("EMPLOYEE");
        if ( employeeRole == null ) {
            employeeRole = new Role("EMPLOYEE");
            roleRepository.save(employeeRole);
        }

        User manuel = userRepository.findByEmail("manueleduardo0320@gmail.com");

        if (manuel == null) {
            userRepository.save(new User(
                "40214519262",
                "Manuel",
                "manueleduardo0320@gmail.com",
                "Espinal",
                "809-698-3809",
                "Street",
                passwordEncoder.encode("password"),
                true,
                new HashSet<>(Arrays.asList(adminRole)),
                null
            ));
        }

        User edilio = userRepository.findByEmail("ediliofco19@hotmail.com");

        if (edilio == null) {
            userRepository.save(new User(
                "11111111111",
                "Edilio",
                "ediliofco19@hotmail.com",
                "Garcia",
                "829-375-9314",
                "Street",
                passwordEncoder.encode("password"),
                true,
                new HashSet<>(Arrays.asList(adminRole)),
                null
            ));
        }
    }
}