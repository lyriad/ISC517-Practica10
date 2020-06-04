package com.web.pucmm.practica10.Config;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    UserRepository repository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user;
        if(authentication.getPrincipal() instanceof Principal) {
             String email = ((Principal) authentication.getPrincipal()).getName();
             user = repository.findByEmail(email);

        }else {
            String email = ((UserDetails)authentication.getPrincipal()).getUsername();
            user = repository.findByEmail(email);
        }
        session.setAttribute("user", user);
        response.sendRedirect("/");
    }
}