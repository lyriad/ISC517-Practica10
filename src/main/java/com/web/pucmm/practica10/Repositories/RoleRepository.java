package com.web.pucmm.practica10.Repositories;

import com.web.pucmm.practica10.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Role findByRole(String role);
}