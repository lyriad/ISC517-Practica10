package com.web.pucmm.practica10.Controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Models.Category;
import com.web.pucmm.practica10.Models.SubCategory;
import com.web.pucmm.practica10.Services.FileUploadService;
import com.web.pucmm.practica10.Services.CategoryService;
import com.web.pucmm.practica10.Services.SubCategoryService;
import com.web.pucmm.practica10.Services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    FileUploadService uploadService;
    
    @GetMapping
    public String list( Model model) {

        model.addAttribute("equipments", equipmentService.all());

        return "/freemarker/equipments/list";
    }

    @GetMapping("/register")
    public String getRegister( Model model, @ModelAttribute("equipment") Equipment equipment, @ModelAttribute("errors") HashMap<String, String> errors) {

        if (errors == null) model.addAttribute("errors", new HashMap<>());
        List<Category> categories = categoryService.all();
        model.addAttribute("action", "Add");
        model.addAttribute("categories", categories);

        return "/freemarker/equipments/register";
    }

    @PostMapping("/register")
    public String postRegister(RedirectAttributes attrs, @RequestParam(name = "image") MultipartFile[] files, @RequestParam(name = "name") String name,
    @RequestParam(name = "category") String categoryId, @RequestParam(name = "subCategory") String subCategoryId, @RequestParam(name = "cantAvailable") int cantAvailable, @RequestParam(name = "costPerDay") float costPerDay) {

        String imagePath = uploadService.uploadFile(files[0], "avatars");

        Category category = categoryService.findById(Long.parseLong(categoryId));
        SubCategory subCategory = subCategoryService.findById(Long.parseLong(subCategoryId));

        Equipment equipment = new Equipment(name, subCategory, category, cantAvailable, costPerDay, imagePath);

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");

        if ( errors.isEmpty() ) {

            equipmentService.create(equipment);
            return String.format("redirect:/equipments");
        
        } else {

            attrs.addFlashAttribute("equipment", equipment);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/equipments/register";
        }
    }
}