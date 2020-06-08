package com.web.pucmm.practica10.Controllers.Api;

import java.util.List;

import com.web.pucmm.practica10.Models.SubCategory;
import com.web.pucmm.practica10.Services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
    
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id_category}/subcategories")
    public List<SubCategory> getSubcategories(@PathVariable long id_category){
        return categoryService.getSubCategories(id_category);
    }
}