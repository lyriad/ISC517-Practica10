package com.web.pucmm.practica10.Services;

import java.util.List;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Models.Rental;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    @Autowired
    private RentalRepository repository;

    public List<Rental> all() {
        return repository.findAll();       
    }

    public Rental findById(long id) {
        return repository.getOne(id);
    }

    public List<Rental> getPending() {
        return repository.getPending();
    }

    public List<Rental> getPendingOrdered() {
        return repository.getPendingOrdered();
    }

    public List<Rental> getDelivered() {
        return repository.getDelivered();
    }

    public List<Rental> getFromClient(long id_client) {
        return repository.getFromClient(id_client);
    }

    public List<Rental> getByEquipment(Equipment equipment) {
        return repository.getByEquipment(equipment.getId());
    }

    @Transactional
    public Rental create(Rental rental) {

        if (repository.existsById(rental.getId())) return null;
        return repository.save(rental);
    }

    @Transactional
    public Rental update(Rental rental) {

        if (!repository.existsById(rental.getId())) return null;
        return repository.save(rental);
    }

    @Transactional
    public Rental createOrUpdate(Rental rental) {
        return repository.save(rental);
    }

    @Transactional
    public boolean delete(Rental rental) {

        if (!repository.existsById(rental.getId())) return false;
        repository.delete(rental);
        return true;
    }
}