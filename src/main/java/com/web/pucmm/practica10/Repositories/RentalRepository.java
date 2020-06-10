package com.web.pucmm.practica10.Repositories;

import java.util.List;

import com.web.pucmm.practica10.Models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    
    @Query("SELECT r FROM Rental r WHERE r.realReturnDate = null")
    List<Rental> getPending();
    
    @Query("SELECT r FROM Rental r WHERE r.realReturnDate = null ORDER BY createdAt DESC")
    List<Rental> getPendingOrdered();

    @Query("SELECT r FROM Rental r WHERE r.realReturnDate != null")
    List<Rental> getDelivered();

    @Query("SELECT r FROM Rental r WHERE r.client.id = :client_id")
    List<Rental> getFromClient(@Param("client_id") long id);

    @Query("SELECT r FROM Rental r WHERE r.equipment.id = :equipment_id")
    List<Rental> getByEquipment(@Param("equipment_id") long id);
}