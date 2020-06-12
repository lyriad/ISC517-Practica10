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
import com.web.pucmm.practica10.Services.RentalService;
import com.web.pucmm.practica10.Services.RoleService;
import com.web.pucmm.practica10.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    UserService userService;

    @Autowired
    FileUploadService uploadService;

    @Autowired
    RoleService roleService;

    @Autowired
    RentalService rentalService;

    @Autowired
    private MessageSource messageSource;

    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    
    @GetMapping
    public String list( Model model) {

        model.addAttribute("clients", userService.getAllClients());

        return "/freemarker/clients/list";
    }

    @GetMapping("/register")
    public String getRegister( Model model, @ModelAttribute("client") User client, @ModelAttribute("errors") HashMap<String, String> errors) {

        if (errors == null) model.addAttribute("errors", new HashMap<>());
        model.addAttribute("action", "Add");

        return "/freemarker/clients/register";
    }

    @PostMapping("/register")
    public String postRegister(RedirectAttributes attrs, Locale locale,  @RequestParam(name = "avatar") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "idNumber") String idNumber, @RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone, @RequestParam(name = "address") String address) {

        String avatarPath = uploadService.uploadFile(files[0], "avatars");
        Role clientRole = roleService.findByName("CLIENT");

        Map<String, String> errors = new HashMap<String, String>();

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

        User client = new User(idNumber, name, email, lastName, phone, address, "", true, new HashSet<>(Arrays.asList(clientRole)), avatarPath);

        if ( errors.isEmpty() ) {

            userService.create(client);
            return String.format("redirect:/clients/%s", idNumber);
        
        } else {

            attrs.addFlashAttribute("client", client);
            attrs.addFlashAttribute("errors", errors);
            return "redirect:/clients/register";
        }
    }

    @GetMapping("/edit/{id_number}")
    public String getEdit( Model model, @PathVariable String id_number, @ModelAttribute("client") User client, @ModelAttribute("errors") HashMap<String, String> errors ) {

        try {
            client.toJson();

        } catch ( Exception e ) {
            client = userService.findByIdNumber(id_number);
        }

        if ( client == null) return "redirect:/error";
        if ( !client.hasRole("CLIENT") ) return "redirect:/error";
        
        if (errors == null) model.addAttribute("errors", new HashMap<>());

        model.addAttribute("client", client);
        model.addAttribute("action", "Edit");
        model.addAttribute("id_number", id_number);

        return "/freemarker/clients/register";
    }

    @PostMapping("/edit/{id_number}")
    public String postEdit(RedirectAttributes attrs, Locale locale, @PathVariable String id_number, @RequestParam(name = "avatar") MultipartFile[] files, @RequestParam(name = "name") String name, @RequestParam(name = "lastName") String lastName, @RequestParam(name = "idNumber") String idNumber, @RequestParam(name = "email") String email, @RequestParam(name = "phone") String phone, @RequestParam(name = "address") String address) {

        User client = userService.findByIdNumber(id_number);
        if ( client == null) return "redirect:/error";
        if ( !client.hasRole("CLIENT") ) return "redirect:/error";

        String avatarPath = uploadService.uploadFile(files[0], "avatars");
        Role clientRole = roleService.findByName("CLIENT");

        Map<String, String> errors = new HashMap<String, String>();

        if ( name == null || name.isEmpty() ) errors.put("name", messageSource.getMessage("form.error.name.empty", null, locale));
        if ( lastName == null || lastName.isEmpty() ) errors.put("lastName", messageSource.getMessage("form.error.lastName.empty", null, locale));
        if ( idNumber == null || idNumber.isEmpty() ) errors.put("idNumber", messageSource.getMessage("form.error.idNumber.empty", null, locale));
        else if ( !client.getIdNumber().equals(idNumber) && userService.existsByIdNumber(idNumber) ) errors.put("idNumber", messageSource.getMessage("form.error.idNumber.taken", null, locale));
        if ( email == null || email.isEmpty() ) errors.put("email", messageSource.getMessage("form.error.email.empty", null, locale));
        else if ( !emailPattern.matcher(email).matches() ) errors.put("email", messageSource.getMessage("form.error.email.invalid", null, locale));
        else if ( !client.getEmail().equals(email) && userService.existsByEmail(email) ) errors.put("email", messageSource.getMessage("form.error.email.taken", null, locale));
        if ( phone == null || phone.isEmpty() ) errors.put("phone", messageSource.getMessage("form.error.phone.empty", null, locale));
        else if ( !phonePattern.matcher(phone).matches() ) errors.put("phone", messageSource.getMessage("form.error.phone.invalid", null, locale));
        if ( address == null || address.isEmpty() ) errors.put("address", messageSource.getMessage("form.error.address.empty", null, locale));

        if ( !errors.isEmpty() ) {

            attrs.addFlashAttribute("user", client);
            attrs.addFlashAttribute("errors", errors);
            return String.format("redirect:/clients/edit/%s", idNumber);
        
        } else {

            client.setIdNumber(idNumber);
            client.setName(name);
            client.setLastName(lastName);
            client.setEmail(email);
            client.setAddress(address);
            client.setPhone(phone);
            client.setRoles(new HashSet<>(Arrays.asList(clientRole)));
            if (avatarPath != null) client.setAvatar(avatarPath);

            userService.update(client);
            return String.format("redirect:/clients/%s", idNumber);
        }
    }

    @GetMapping("/{id_number}")
    public String getView( Model model, @PathVariable String id_number ) {

        User client = userService.findByIdNumber(id_number);
        if ( client == null) return "redirect:/error";
        if ( !client.hasRole("CLIENT") ) return "redirect:/error";

        model.addAttribute("client", client);
        model.addAttribute("rentals", rentalService.getFromClient(client.getId()));

        return "/freemarker/clients/view";
    }
}