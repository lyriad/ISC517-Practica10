package com.web.pucmm.practica10.Services;

import java.util.List;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.SubCategory;
import com.web.pucmm.practica10.Repositories.SubCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository repository;

    public List<SubCategory> all() {
        return repository.findAll();       
    }

    public SubCategory findById(long id) {
        return repository.getOne(id);
    }

    public SubCategory findByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    public SubCategory create(SubCategory subCategory) {

        if (repository.existsById(subCategory.getId())) return null;
        return repository.save(subCategory);
    }

    @Transactional
    public SubCategory update(SubCategory subCategory) {

        if (!repository.existsById(subCategory.getId())) return null;
        return repository.save(subCategory);
    }

    @Transactional
    public SubCategory createOrUpdate(SubCategory subCategory) {
        return repository.save(subCategory);
    }

    @Transactional
    public boolean delete(SubCategory subCategory) {

        if (!repository.existsById(subCategory.getId())) return false;
        repository.delete(subCategory);
        return true;
    }
}