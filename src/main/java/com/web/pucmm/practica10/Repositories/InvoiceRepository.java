package com.web.pucmm.practica10.Repositories;

import java.util.List;
import com.web.pucmm.practica10.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    @Query("SELECT i FROM Invoice i WHERE i.client.id = :client_id")
    List<Invoice> getFromClient(@Param("client_id") long id);

    @Query("SELECT i FROM Invoice i WHERE i.employee.id = :employee_id")
    List<Invoice> getFromEmployee(@Param("employee_id") long id);

    @Query("SELECT i FROM Invoice i WHERE i.paid = false")
    List<Invoice> getPending();

    @Query("SELECT i FROM Invoice i WHERE i.paid = true")
    List<Invoice> getPaid();
}