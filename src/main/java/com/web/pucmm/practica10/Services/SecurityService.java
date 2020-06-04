package com.web.pucmm.practica10.Services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import com.web.pucmm.practica10.Models.Role;
import com.web.pucmm.practica10.Models.User;
import com.web.pucmm.practica10.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException("");

        if (user.hasRole("CLIENT")) throw new UsernameNotFoundException("");

        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, grantedAuthorities);
    }
}