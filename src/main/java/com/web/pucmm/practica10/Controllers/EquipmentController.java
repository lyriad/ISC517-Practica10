package com.web.pucmm.practica10.Controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.web.pucmm.practica10.Models.Equipment;
import com.web.pucmm.practica10.Models.Category;
import com.web.pucmm.practica10.Models.SubCategory;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.FileUploadService;
import com.web.pucmm.practica10.Services.CategoryService;
import com.web.pucmm.practica10.Services.SubCategoryService;
import com.web.pucmm.practica10.Services.UserService;
import com.web.pucmm.practica10.Services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    UserService userService;
    
    @GetMapping
    public String list( Principal principal, Model model) {

        User user = userService.getLoggedUser(principal);
        model.addAttribute("equipments", equipmentService.all());
        model.addAttribute("auth", user);

        return "/freemarker/equipments/list";
    }

    @GetMapping("/register")
    public String getRegister( Principal principal, Model model, @ModelAttribute("equipment") Equipment equipment, @ModelAttribute("errors") HashMap<String, String> errors) {

        
        User user = userService.getLoggedUser(principal);
        if (errors == null) model.addAttribute("errors", new HashMap<>());
        model.addAttribute("action", "Add");
        model.addAttribute("categories", categoryService.all());
        model.addAttribute("auth", user);

        return "/freemarker/equipments/register";
    }

    @PostMapping("/register")
    public String postRegister(RedirectAttributes attrs, @RequestParam(name = "image") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "id_category") String id_category, @RequestParam(name = "id_subcategory") String id_subcategory, @RequestParam(name = "cantAvailable") int cantAvailable, @RequestParam(name = "costPerDay") float costPerDay) {

        String imagePath = uploadService.uploadFile(files[0], "avatars");

        Map<String, String> errors = new HashMap<String, String>();

        Category category = null;
        if(id_category != null && !id_category.isEmpty())
            category = categoryService.findById(Long.parseLong(id_category));
        if (category == null) errors.put("category", "You must select a valid category!");

        SubCategory subCategory = null;
        if(id_subcategory != null && !id_subcategory.isEmpty())
            subCategory = subCategoryService.findById(Long.parseLong(id_subcategory));

        Equipment equipment = new Equipment(name, subCategory, category, cantAvailable, costPerDay, imagePath);

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");
        if ( cantAvailable < 0 ) errors.put("cantAvailable", "This must be positive!");
        if ( costPerDay < 0 ) errors.put("costPerDay", "This must be positive!");

        if ( errors.isEmpty() ) {

            equipmentService.create(equipment);
            return String.format("redirect:/equipments");
        
        } else {

            attrs.addFlashAttribute("equipment", equipment);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/equipments/register";
        }
    }

    @GetMapping("/edit/{id_equipment}")
    public String getEdit( Principal principal, Model model, @PathVariable long id_equipment, @ModelAttribute("employee") Equipment equipment, @ModelAttribute("errors") HashMap<String, String> errors ) {
        
        User user = userService.getLoggedUser(principal);
        try {
            equipment.getName().isEmpty();

        } catch ( Exception e ) {
            equipment = equipmentService.findById(id_equipment);
        }

        if ( equipment == null) return "redirect:/error";
        
        if (errors == null) model.addAttribute("errors", new HashMap<>());

        List<Category> categories = categoryService.all();
        model.addAttribute("categories", categories);
        model.addAttribute("equipment", equipment);
        model.addAttribute("action", "Edit");
        model.addAttribute("auth", user);

        return "/freemarker/equipments/register";
    }

    @PostMapping("/edit/{id_equipment}")
    public String postEdit(RedirectAttributes attrs, @PathVariable long id_equipment, @RequestParam(name = "image") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "id_category") String id_category, @RequestParam(name = "id_subcategory") String id_subcategory, @RequestParam(name = "cantAvailable") int cantAvailable, @RequestParam(name = "costPerDay") float costPerDay) {

        Equipment equipment = equipmentService.findById(id_equipment);
        if ( equipment == null) return "redirect:/error";

        String imagePath = uploadService.uploadFile(files[0], "avatars");

        Map<String, String> errors = new HashMap<String, String>();

        Category category = null;
        if(id_category != null && !id_category.isEmpty())
            category = categoryService.findById(Long.parseLong(id_category));
        if (category == null) errors.put("category", "You must select a valid category!");

        SubCategory subCategory = null;
        if(id_subcategory != null && !id_subcategory.isEmpty())
            subCategory = subCategoryService.findById(Long.parseLong(id_subcategory));
    
        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");
        if ( cantAvailable < 0 ) errors.put("cantAvailable", "This must be positive!");
        if ( costPerDay < 0 ) errors.put("costPerDay", "This must be positive!");

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("equipment", equipment);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/equipments/edit/%l", id_equipment);

        } else {
            equipment.setName(name);
            if (Long.parseLong(id_category) != equipment.getCategory().getId()) equipment.setCategory(category);
            if (id_subcategory!= null && !id_subcategory.isEmpty() && (Long.parseLong(id_subcategory) != equipment.getSubCategory().getId()))
                equipment.setSubCategory(subCategory);
            equipment.setCantAvailable(cantAvailable);
            equipment.setCostPerDay(costPerDay);
            if (imagePath != null) equipment.setImage(imagePath);

            equipmentService.update(equipment);
            return String.format("redirect:/equipments");
        }
    }

    @GetMapping("/{id_equipment}")
    public String getView( Principal principal, Model model, @PathVariable long id_equipment ) {

        User user = userService.getLoggedUser(principal);
        Equipment equipment = equipmentService.findById(id_equipment);
        if ( equipment == null) return "redirect:/error";

        model.addAttribute("equipment", equipment);
        model.addAttribute("auth", user);

        return "/freemarker/equipments/view";
    }
}