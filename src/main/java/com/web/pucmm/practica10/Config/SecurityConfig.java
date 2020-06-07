package com.web.pucmm.practica10.Config;

import com.web.pucmm.practica10.Services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configurable
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(securityService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/**", "/css/**", "/js/**", "/images/**", "/vendor/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/avatars/**", "/equipments/**", "/clients/**").authenticated()
                .antMatchers("/employees/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                    .loginPage("/auth/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/")
                    .successHandler(authenticationSuccessHandler)
                    .permitAll()
                .and()
                .logout().logoutUrl("/auth/logout").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}