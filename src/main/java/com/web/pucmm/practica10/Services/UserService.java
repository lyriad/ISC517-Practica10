package com.web.pucmm.practica10.Services;

import java.util.List;
import java.security.Principal;
import org.springframework.security.core.userdetails.UserDetails;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> all() {
        return repository.findAll();       
    }

    public User getLoggedUser(Principal principal) {
        String email = principal.getName();
        User user = repository.findByEmail(email);
        return user;
    }

    public User findById(long id) {
        return repository.getOne(id);
    }

    public User findByIdNumber(String idNumber) {
        return repository.findByIdNumber(idNumber);
    }

    public User findByName(String name) {
        return repository.findByName(name);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public User findByFullName(String name, String lastName) {
        return repository.findByFullName(name, lastName);
    }

    public List<User> getAllEmployees() {
        return repository.getAllEmployees();
    }

    public List<User> getAllClients() {
        return repository.getAllClients();
    }

    public boolean existsByIdNumber(String idNumber) {
        return repository.existsByIdNumber(idNumber) == 1;
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email) == 1;
    }

    public int getCountPendingInvoices(long id_employee) {
        return repository.getCountPendingInvoices(id_employee);
    }

    @Transactional
    public User create(User user) {

        if (repository.existsById(user.getId())) return null;
        return repository.save(user);
    }

    @Transactional
    public User update(User user) {

        if (!repository.existsById(user.getId())) return null;
        return repository.save(user);
    }

    @Transactional
    public User createOrUpdate(User user) {
        return repository.save(user);
    }

    @Transactional
    public boolean delete(User user) {

        if (!repository.existsById(user.getId())) return false;
        repository.delete(user);
        return true;
    }
}