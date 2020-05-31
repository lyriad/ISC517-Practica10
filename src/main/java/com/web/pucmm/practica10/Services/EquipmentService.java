package com.web.pucmm.practica10.Services;

import java.util.List;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repository;

    public List<Equipment> all() {
        return repository.findAll();       
    }

    public Equipment findById(long id) {
        return repository.getOne(id);
    }

    public Equipment findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public Equipment create(Equipment equipment) {

        if (repository.existsById(equipment.getId())) return null;
        return repository.save(equipment);
    }

    @Transactional
    public Equipment update(Equipment equipment) {

        if (!repository.existsById(equipment.getId())) return null;
        return repository.save(equipment);
    }

    @Transactional
    public Equipment createOrUpdate(Equipment equipment) {
        return repository.save(equipment);
    }

    @Transactional
    public boolean delete(Equipment equipment) {

        if (!repository.existsById(equipment.getId())) return false;
        repository.delete(equipment);
        return true;
    }
}