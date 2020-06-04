package com.web.pucmm.practica10.Repositories;

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
    
}