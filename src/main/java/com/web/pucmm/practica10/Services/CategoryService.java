package com.web.pucmm.practica10.Services;

import java.util.List;
import javax.transaction.Transactional;
import com.web.pucmm.practica10.Models.Category;
import com.web.pucmm.practica10.Models.SubCategory;
import com.web.pucmm.practica10.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> all() {
        return repository.findAll();       
    }

    public Category findById(long id) {
        return repository.getOne(id);
    }

    public Category findByName(String name) {
        return repository.findByName(name);
    }

    public List<SubCategory> getSubCategories(long id_category) {
        return repository.getSubCategoriesByCategory(id_category);
    }

    @Transactional
    public Category create(Category category) {

        if (repository.existsById(category.getId())) return null;
        return repository.save(category);
    }

    @Transactional
    public Category update(Category category) {

        if (!repository.existsById(category.getId())) return null;
        return repository.save(category);
    }

    @Transactional
    public Category createOrUpdate(Category category) {
        return repository.save(category);
    }

    @Transactional
    public boolean delete(Category category) {

        if (!repository.existsById(category.getId())) return false;
        repository.delete(category);
        return true;
    }
}