package com.web.pucmm.practica10.Repositories;

import com.web.pucmm.practica10.Models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
    Equipment findByName(String name);

    @Query("SELECT e FROM Equipment e WHERE LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')")
    Equipment searchFirstByNameContaining(@Param("name") String name);
}