package com.web.pucmm.practica10.Repositories;

import java.util.List;
import com.web.pucmm.practica10.Models.Category;
import com.web.pucmm.practica10.Models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Query("SELECT s FROM SubCategory s WHERE s.category.id = :category_id")
    List<SubCategory> getSubCategoriesByCategory(@Param("category_id") long id);
}