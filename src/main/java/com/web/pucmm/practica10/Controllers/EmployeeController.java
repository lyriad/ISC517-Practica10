package com.web.pucmm.practica10.Controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import com.web.pucmm.practica10.Models.Role;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Services.FileUploadService;
import com.web.pucmm.practica10.Services.RoleService;
import com.web.pucmm.practica10.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    private MessageSource messageSource;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    
    @GetMapping
    public String list( Model model ) {

        model.addAttribute("employees", userService.getAllEmployees());

        return "/freemarker/employees/list";
    }

    @GetMapping("/register")
    public String getRegister( Model model, @ModelAttribute("employee") User employee, @ModelAttribute("errors") HashMap<String, String> errors) {

        if (errors == null) model.addAttribute("errors", new HashMap<>());
        model.addAttribute("action", "Add");

        return "/freemarker/employees/register";
    }

    @PostMapping("/register")
    public String postRegister(RedirectAttributes attrs, Locale locale, @RequestParam(name = "avatar") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "idNumber") String idNumber, @RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone, @RequestParam(name = "role") String role, @RequestParam(name = "address") String address, @RequestParam(name = "password") String password, @RequestParam(name = "confirmPassword") String confirmPassword) {

        String avatarPath = uploadService.uploadFile(files[0], "avatars");
        Role userRole = roleService.findByName(role);

        User employee = new User(idNumber, name, email, lastName, phone, address, encoder.encode(password), true, new HashSet<>(Arrays.asList(userRole)), avatarPath);

        Map<String, String> errors = new HashMap<String, String>();

        if ( userRole == null ) errors.put("role", messageSource.getMessage("employee.form.error.role.invalid", null, locale));
        if ( name == null || name.isEmpty() ) errors.put("name", messageSource.getMessage("form.error.name.empty", null, locale));
        if ( lastName == null || lastName.isEmpty() ) errors.put("lastName", messageSource.getMessage("form.error.lastName.empty", null, locale));
        if ( idNumber == null || idNumber.isEmpty() ) errors.put("idNumber", messageSource.getMessage("form.error.idNumber.empty", null, locale));
        else if ( userService.existsByIdNumber(idNumber) ) errors.put("idNumber", messageSource.getMessage("form.error.idNumber.taken", null, locale));
        if ( email == null || email.isEmpty() ) errors.put("email", messageSource.getMessage("form.error.email.empty", null, locale));
        else if ( !emailPattern.matcher(email).matches() ) errors.put("email", messageSource.getMessage("form.error.email.invalid", null, locale));
        else if ( userService.existsByEmail(email) ) errors.put("email", messageSource.getMessage("form.error.email.taken", null, locale));
        if ( phone == null || phone.isEmpty() ) errors.put("phone", messageSource.getMessage("form.error.phone.empty", null, locale));
        else if ( !phonePattern.matcher(phone).matches() ) errors.put("phone", messageSource.getMessage("form.error.phone.invalid", null, locale));
        if ( address == null || address.isEmpty() ) errors.put("address", messageSource.getMessage("form.error.address.empty", null, locale));
        if ( password == null || password.isEmpty() ) errors.put("password", messageSource.getMessage("form.error.password.empty", null, locale));
        else if ( confirmPassword == null || confirmPassword.isEmpty() ) errors.put("confirmPassword", messageSource.getMessage("form.error.confirmPassword.empty", null, locale));
        else if ( !password.equals(confirmPassword) ) errors.put("confirmPassword", messageSource.getMessage("form.error.confirmPassword.match", null, locale));

        if ( errors.isEmpty() ) {

            userService.create(employee);
            return String.format("redirect:/employees/%s", idNumber);
        
        } else {

            attrs.addFlashAttribute("employee", employee);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/employees/register";
        }
    }

    @GetMapping("/edit/{id_number}")
    public String getEdit( Model model, @PathVariable String id_number, @ModelAttribute("employee") User employee, @ModelAttribute("errors") HashMap<String, String> errors ) {

        try {
            employee.toJson();

        } catch ( Exception e ) {
            employee = userService.findByIdNumber(id_number);
        }

        if ( employee == null) return "redirect:/error";
        if ( employee.hasRole("CLIENT") ) return "redirect:/error";
        
        if (errors == null) model.addAttribute("errors", new HashMap<>());

        model.addAttribute("employee", employee);
        model.addAttribute("action", "Edit");
        model.addAttribute("id_number", id_number);

        return "/freemarker/employees/register";
    }

    @PostMapping("/edit/{id_number}")
    public String postEdit(RedirectAttributes attrs, Locale locale, @PathVariable String id_number, @RequestParam(name = "avatar") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "idNumber") String idNumber, @RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone, @RequestParam(name = "role") String role, @RequestParam(name = "address") String address, @RequestParam(name = "password") String password, @RequestParam(name = "confirmPassword") String confirmPassword) {

        User employee = userService.findByIdNumber(id_number);
        if ( employee == null) return "redirect:/error";
        if ( employee.hasRole("CLIENT") ) return "redirect:/error";

        String avatarPath = uploadService.uploadFile(files[0], "avatars");
        Role userRole = roleService.findByName(role);

        Map<String, String> errors = new HashMap<String, String>();

        if ( userRole == null ) errors.put("role", messageSource.getMessage("employee.form.error.role.invalid", null, locale));
        if ( name == null || name.isEmpty() ) errors.put("name", messageSource.getMessage("form.error.name.empty", null, locale));
        if ( lastName == null || lastName.isEmpty() ) errors.put("lastName", messageSource.getMessage("form.error.lastName.empty", null, locale));
        if ( idNumber == null || idNumber.isEmpty() ) errors.put("idNumber", messageSource.getMessage("form.error.idNumber.empty", null, locale));
        else if ( !employee.getIdNumber().equals(idNumber) && userService.existsByIdNumber(idNumber) ) errors.put("idNumber", messageSource.getMessage("form.error.idNumber.taken", null, locale));
        if ( email == null || email.isEmpty() ) errors.put("email", messageSource.getMessage("form.error.email.empty", null, locale));
        else if ( !emailPattern.matcher(email).matches() ) errors.put("email", messageSource.getMessage("form.error.email.invalid", null, locale));
        else if ( !employee.getEmail().equals(email) && userService.existsByEmail(email) ) errors.put("email", messageSource.getMessage("form.error.email.taken", null, locale));
        if ( phone == null || phone.isEmpty() ) errors.put("phone", messageSource.getMessage("form.error.phone.empty", null, locale));
        else if ( !phonePattern.matcher(phone).matches() ) errors.put("phone", messageSource.getMessage("form.error.phone.invalid", null, locale));
        if ( address == null || address.isEmpty() ) errors.put("address", messageSource.getMessage("form.error.address.empty", null, locale));

        if (password != null && !password.isEmpty() && confirmPassword != null || !confirmPassword.isEmpty() && !password.equals(confirmPassword)) {
            errors.put("confirmPassword", messageSource.getMessage("form.error.confirmPassword.match", null, locale));
        }

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("employee", employee);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/employees/edit/%s", idNumber);
        
        } else {

            employee.setIdNumber(idNumber);
            employee.setName(name);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setAddress(address);
            employee.setPhone(phone);
            employee.setRoles(new HashSet<>(Arrays.asList(userRole)));
            if (avatarPath != null) employee.setAvatar(avatarPath);
            if (password != null && !password.isEmpty()) employee.setPassword(encoder.encode(password));

            userService.update(employee);
            return String.format("redirect:/employees/%s", idNumber);
        }
    }

    @GetMapping("/{id_number}")
    public String getView( Model model, @PathVariable String id_number ) {

        User employee = userService.findByIdNumber(id_number);
        if ( employee == null) return "redirect:/error";
        if ( employee.hasRole("CLIENT") ) return "redirect:/error";

        model.addAttribute("employee", employee);

        return "/freemarker/employees/view";
    }
}