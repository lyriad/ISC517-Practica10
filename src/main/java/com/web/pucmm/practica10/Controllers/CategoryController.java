package com.web.pucmm.practica10.Controllers;

import com.web.pucmm.practica10.Models.Category;
import com.web.pucmm.practica10.Models.SubCategory;
import com.web.pucmm.practica10.Services.CategoryService;
import com.web.pucmm.practica10.Services.SubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping
    public String list( Model model) {
        model.addAttribute("categories", categoryService.all());

        return "/freemarker/categories/list";
    }

    @GetMapping("/register")
    public String getRegister(Model model, @ModelAttribute("category") Category Category, @ModelAttribute("errors") HashMap<String, String> errors) {

        if (errors == null) model.addAttribute("errors", new HashMap<>());
        model.addAttribute("action", "Add");

        return "/freemarker/categories/register";
    }

    @PostMapping("/register")
    public String postRegister(RedirectAttributes attrs, @RequestParam(name = "name") String name, @RequestParam(name = "description") String description) {

        Category category = new Category(name, description);

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\'t be empty!");

        if ( errors.isEmpty() ) {

            categoryService.create(category);
            return "redirect:/categories";

        } else {

            attrs.addFlashAttribute("category", category);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/categories/register";
        }
    }

    @GetMapping("/edit/{id_category}")
    public String getEdit( Model model, @PathVariable long id_category, @ModelAttribute("category") Category category, @ModelAttribute("errors") HashMap<String, String> errors ) {

        try {
            category.getName().isEmpty();

        } catch ( Exception e ) {
            category = categoryService.findById(id_category);
        }
        
        if ( category == null) return "redirect:/error";
        
        if (errors == null) model.addAttribute("errors", new HashMap<>());

        model.addAttribute("category", category);
        model.addAttribute("action", "Edit");

        return "/freemarker/categories/register";
    }

    @PostMapping("/edit/{id_category}")
    public String postEdit(RedirectAttributes attrs, @PathVariable long id_category, @RequestParam(name = "name") String name, @RequestParam(name = "description") String description) {

        Category category = categoryService.findById(id_category);
        if ( category == null) return "redirect:/error";

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\'t be empty!");

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("category", category);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/categories/edit/%s", id_category );

        } else {

            category.setName(name);
            category.setDescription(description);

            categoryService.update(category);
            return String.format("redirect:/categories");
        }
    }

    @GetMapping("/{id_category}/subcategories")
    public String getSubcategory(Model model, @PathVariable long id_category, @ModelAttribute("subcategory") SubCategory subCategory, @ModelAttribute("errors") HashMap<String, String> errors) {

        if (errors == null) model.addAttribute("errors", new HashMap<>());
        Category category = categoryService.findById(id_category);
        
        if ( category == null) return "redirect:/error";

        model.addAttribute("category", category);
        model.addAttribute("SubCategories", categoryService.getSubCategories(category.getId()));
        model.addAttribute("action", "Add");

        return "/freemarker/categories/subcategories";
    }

    @PostMapping("/{id_category}/subcategories/register")
    public String registerSubcategory(RedirectAttributes attrs, @PathVariable long id_category, @RequestParam(name = "name") String name) {

        Category category = categoryService.findById(id_category);
        SubCategory subcategory = new SubCategory(name, category);

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\'t be empty!");

        if ( errors.isEmpty() ) {

            subCategoryService.create(subcategory);
            System.out.println(subcategory);
            return String.format("redirect:/categories/%d/subcategories", id_category);

        } else {

            attrs.addFlashAttribute("subcategory", subcategory);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/categories/%d/subcategories", id_category);
        }
    }

    @GetMapping("/{id_category}/subcategories/{id_subcategory}")
    public String getEditSubcategory(RedirectAttributes attrs, Model model, @PathVariable long id_category, @PathVariable long id_subcategory, @ModelAttribute("subcategory") SubCategory subCategory, @ModelAttribute("errors") HashMap<String, String> errors ) {

        try {
            subCategory.getName().isEmpty();

        } catch ( Exception e ) {
            subCategory = subCategoryService.findById(id_subcategory);
        }

        Category category = categoryService.findById(id_category);
        
        if ( subCategory == null || category == null) return "redirect:/error";
        System.out.println(subCategory.toJson());
        
        if (errors == null) model.addAttribute("errors", new HashMap<>());

        model.addAttribute("subcategory", subCategory);
        model.addAttribute("category", category);
        model.addAttribute("SubCategories", categoryService.getSubCategories(category.getId()));
        model.addAttribute("action", "Edit");

        return "/freemarker/categories/subcategories";
    }

    @PostMapping("/{id_category}/subcategories/{id_subcategory}")
    public String editSubcategory(RedirectAttributes attrs, @PathVariable long id_subcategory, @PathVariable long id_category, @RequestParam(name = "name") String name) {

        SubCategory subCategory = subCategoryService.findById(id_subcategory);
        if ( subCategory == null) return "redirect:/error";

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\'t be empty!");

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("subcategory", subCategory);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/categories/%d/subcategories/%d", id_category, id_subcategory);

        } else {

            subCategory.setName(name);
            subCategoryService.update(subCategory);
            return String.format("redirect:/categories/%d/subcategories", id_category);
        }
    }
}