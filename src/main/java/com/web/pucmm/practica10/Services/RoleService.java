package com.web.pucmm.practica10.Services;

import java.util.List;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.Role;
import com.web.pucmm.practica10.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public List<Role> all() {
        return repository.findAll();       
    }

    public Role findById(long id) {
        return repository.getOne(id);
    }

    public Role findByName(String name) {
        return repository.findByRole(name);
    }

    @Transactional
    public Role create(Role role) {

        if (repository.existsById(role.getId())) return null;
        return repository.save(role);
    }

    @Transactional
    public Role update(Role role) {

        if (!repository.existsById(role.getId())) return null;
        return repository.save(role);
    }

    @Transactional
    public Role createOrUpdate(Role role) {
        return repository.save(role);
    }

    @Transactional
    public boolean delete(Role role) {

        if (!repository.existsById(role.getId())) return false;
        repository.delete(role);
        return true;
    }
}