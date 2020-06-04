package com.web.pucmm.practica10.Controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;
import com.web.pucmm.practica10.Models.Role;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.FileUploadService;
import com.web.pucmm.practica10.Services.RoleService;
import com.web.pucmm.practica10.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    UserService userService;

    @Autowired
    FileUploadService uploadService;

    @Autowired
    RoleService roleService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    
    @GetMapping
    public String list( Model model) {

        model.addAttribute("employees", userService.getAllEmployees());

        return "/freemarker/employees/list";
    }

    @GetMapping("/register")
    public String getRegister( Model model, @ModelAttribute("user") User user, @ModelAttribute("errors") HashMap<String, String> errors) {

        if (errors == null) model.addAttribute("errors", new HashMap<>());
        model.addAttribute("action", "Add");

        return "/freemarker/employees/register";
    }

    @PostMapping("/register")
    public String postRegister(RedirectAttributes attrs, @RequestParam(name = "avatar") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "idNumber") String idNumber, @RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone, @RequestParam(name = "role") String role, @RequestParam(name = "address") String address, @RequestParam(name = "password") String password, @RequestParam(name = "confirmPassword") String confirmPassword) {

        String avatarPath = uploadService.uploadFile(files[0], "avatars", idNumber);
        Role userRole = roleService.findByName(role);

        User user = new User(idNumber, name, email, lastName, phone, address, encoder.encode(password), true, new HashSet<>(Arrays.asList(userRole)), avatarPath);

        Map<String, String> errors = new HashMap<String, String>();

        if ( avatarPath == null || avatarPath.isEmpty() ) errors.put("avatar", "The image can\' be empty!");
        if ( userRole == null ) errors.put("role", "The selected role is invalid!");
        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");
        if ( lastName == null || lastName.isEmpty() ) errors.put("lastName", "The last name can\' be empty!");
        if ( idNumber == null || idNumber.isEmpty() ) errors.put("idNumber", "The id number name can\' be empty!");
        if ( email == null || email.isEmpty() ) errors.put("email", "The email can\' be empty!");
        else if ( !emailPattern.matcher(email).matches() ) errors.put("email", "You must enter a valid email address!");
        if ( phone == null || phone.isEmpty() ) errors.put("phone", "The phone can\' be empty!");
        else if ( !phonePattern.matcher(phone).matches() ) errors.put("phone", "You must enter a valid phone number!");
        if ( address == null || address.isEmpty() ) errors.put("address", "The address can\' be empty!");
        if ( password == null || password.isEmpty() ) errors.put("password", "The password can\' be empty!");
        else if ( confirmPassword == null || confirmPassword.isEmpty() ) errors.put("confirmPassword", "You must confirm the password!");
        else if ( !password.equals(confirmPassword) ) errors.put("confirmPassword", "The passwords do not match!");

        if ( errors.isEmpty() ) {

            userService.create(user);
            return "redirect:/employees";
        
        } else {

            attrs.addFlashAttribute("user", user);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/employees/register";
        }
    }

    @GetMapping("/edit/{id_number}")
    public String getEdit( Model model, @PathVariable String id_number, @ModelAttribute("user") User user, @ModelAttribute("errors") HashMap<String, String> errors ) {

        try {
            user.toJson();

        } catch ( Exception e ) {
            user = userService.findByIdNumber(id_number);
        }

        if ( user == null) return "redirect:/404";
        
        if (errors == null) model.addAttribute("errors", new HashMap<>());

        model.addAttribute("user", user);
        model.addAttribute("action", "Edit");
        model.addAttribute("id_number", id_number);

        return "/freemarker/employees/register";
    }

    @PostMapping("/edit/{id_number}")
    public String postEdit(RedirectAttributes attrs, @PathVariable String id_number, @RequestParam(name = "avatar") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "idNumber") String idNumber, @RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone, @RequestParam(name = "role") String role, @RequestParam(name = "address") String address, @RequestParam(name = "password") String password, @RequestParam(name = "confirmPassword") String confirmPassword) {

        User user = userService.findByIdNumber(id_number);
        if ( user == null) return "redirect:/404";

        String avatarPath = uploadService.uploadFile(files[0], "avatars", idNumber);
        Role userRole = roleService.findByName(role);

        Map<String, String> errors = new HashMap<String, String>();

        if ( avatarPath == null || avatarPath.isEmpty() ) errors.put("avatar", "The image can\' be empty!");
        if ( userRole == null ) errors.put("role", "The selected role is invalid!");
        if ( name == null || name.isEmpty() ) errors.put("name", "The name can\' be empty!");
        if ( lastName == null || lastName.isEmpty() ) errors.put("lastName", "The last name can\' be empty!");
        if ( idNumber == null || idNumber.isEmpty() ) errors.put("idNumber", "The id number name can\' be empty!");
        if ( email == null || email.isEmpty() ) errors.put("email", "The email can\' be empty!");
        else if ( !emailPattern.matcher(email).matches() ) errors.put("email", "You must enter a valid email address!");
        if ( phone == null || phone.isEmpty() ) errors.put("phone", "The phone can\' be empty!");
        else if ( !phonePattern.matcher(phone).matches() ) errors.put("phone", "You must enter a valid phone number!");
        if ( address == null || address.isEmpty() ) errors.put("address", "The address can\' be empty!");
        if ( password == null || password.isEmpty() ) errors.put("password", "The password can\' be empty!");
        else if ( confirmPassword == null || confirmPassword.isEmpty() ) errors.put("confirmPassword", "You must confirm the password!");
        else if ( !password.equals(confirmPassword) ) errors.put("confirmPassword", "The passwords do not match!");

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("user", user);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/employees/edit/%s", idNumber);
        
        } else {

            user.setIdNumber(idNumber);
            user.setName(name);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setAddress(address);
            user.setPhone(phone);
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            user.setAvatar(avatarPath);
            user.setPassword(encoder.encode(password));

            userService.update(user);
            return String.format("redirect:/employees/%s", idNumber);
        }
    }

    @GetMapping("/{id_number}")
    public String getView( Model model, @PathVariable String id_number ) {

        User user = userService.findByIdNumber(id_number);
        if ( user == null) return "redirect:/404";

        model.addAttribute("user", user);

        return "/freemarker/employees/view";
    }
}