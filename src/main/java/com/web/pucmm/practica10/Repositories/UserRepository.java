package com.web.pucmm.practica10.Repositories;

import java.util.List;
import com.web.pucmm.practica10.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdNumber(String idNumber);
    User findByName(String name);
    User findByEmail(String email);
    User findByLastName(String lastName);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.lastName = :lastName")
    User findByFullName(@Param("name") String name, @Param("lastName") String lastName);

    @Query("SELECT u, r.role FROM User u JOIN u.roles r WHERE r.role = 'ADMIN' OR r.role = 'EMPLOYEE'")
    List<User> getAllEmployees();

    @Query("SELECT u, r.role FROM User u JOIN u.roles r WHERE r.role = 'CLIENT'")
    List<User> getAllClients();

    @Query("SELECT COUNT(1) FROM User u WHERE u.idNumber = :idNumber")
    int existsByIdNumber(@Param("idNumber") String idNumber);

    @Query("SELECT COUNT(1) FROM User u WHERE u.email = :email")
    int existsByEmail(@Param("email") String email);

    @Query("SELECT COUNT(*) FROM Invoice i WHERE i.employee.id = :id_employee AND i.paid = TRUE")
    int getCountPendingInvoices(@Param("id_employee") long id_employee);
    
}