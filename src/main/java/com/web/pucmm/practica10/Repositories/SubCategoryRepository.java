package com.web.pucmm.practica10.Repositories;

import com.web.pucmm.practica10.Models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    SubCategory findByName(String name);
}