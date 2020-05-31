package com.web.pucmm.practica10.Repositories;

import com.web.pucmm.practica10.Models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
    Equipment findByName(String name);
}