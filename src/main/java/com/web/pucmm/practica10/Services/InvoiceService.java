package com.web.pucmm.practica10.Services;

import java.util.List;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.Invoice;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public List<Invoice> all() {
        return repository.findAll();       
    }

    public Invoice findById(long id) {
        return repository.getOne(id);
    }

    List<Invoice> getFromClient(User client) {
        return repository.getFromClient(client.getId());
    }

    List<Invoice> getFromEmployee(User employee) {
        return repository.getFromEmployee(employee.getId());
    }

    public List<Invoice> getPending() {
        return repository.getPending();
    }

    public List<Invoice> getPaid() {
        return repository.getPaid();
    }

    @Transactional
    public Invoice create(Invoice invoice) {

        if (repository.existsById(invoice.getId())) return null;
        return repository.save(invoice);
    }

    @Transactional
    public Invoice update(Invoice invoice) {

        if (!repository.existsById(invoice.getId())) return null;
        return repository.save(invoice);
    }

    @Transactional
    public Invoice createOrUpdate(Invoice invoice) {
        return repository.save(invoice);
    }

    @Transactional
    public boolean delete(Invoice invoice) {

        if (!repository.existsById(invoice.getId())) return false;
        repository.delete(invoice);
        return true;
    }
}