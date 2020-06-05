package com.web.pucmm.practica10.Controllers;

import com.web.pucmm.practica10.Models.Category;
import com.web.pucmm.practica10.Services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService CategoryService;

    @GetMapping
    public String list( Model model) {
        model.addAttribute("categories", CategoryService.all());

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

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");

        if ( errors.isEmpty() ) {

            CategoryService.create(category);
            return "redirect:/categories";

        } else {

            attrs.addFlashAttribute("category", category);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/categories/register";
        }
    }

    @GetMapping("/edit/{id}")
    public String getEdit( Model model, @PathVariable String id, @ModelAttribute("category") Category category, @ModelAttribute("errors") HashMap<String, String> errors ) {

        try {
            category.toJson();

        } catch ( Exception e ) {
            category = CategoryService.findById(Long.parseLong(id));
        }

        if ( category == null) return "redirect:/404";

        if (errors == null) model.addAttribute("errors", new HashMap<>());

        model.addAttribute("category", category);
        model.addAttribute("action", "Edit");

        return "/freemarker/categories/register";
    }

    @PostMapping("/edit/{id_number}")
    public String postEdit(RedirectAttributes attrs, @PathVariable String id_number, @RequestParam(name = "name") String name, @RequestParam(name = "description") String description) {

        Category category = CategoryService.findById(Long.parseLong(id_number));
        if ( category == null) return "redirect:/404";

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("category", category);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/categories/edit/%s", id_number );

        } else {

            category.setName(name);
            category.setDescription(description);

            CategoryService.update(category);
            return String.format("redirect:/categories/%s", id_number);
        }
    }

    @GetMapping("/{id_number}")
    public String getView( Model model, @PathVariable String id_number ) {

        Category category = CategoryService.findById(Long.parseLong(id_number));
        if ( category == null) return "redirect:/404";

        model.addAttribute("category", category);

        return "/freemarker/categories/view";
    }
}