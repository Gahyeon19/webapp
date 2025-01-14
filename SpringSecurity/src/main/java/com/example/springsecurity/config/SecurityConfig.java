package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {      //암호화
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                            .username("user")
//                            .password("{noop}"+"1111")?
                            .password(passwordEncoder().encode("1111"))
                            .roles("USER")
                            .build();
        UserDetails admin = User.builder()
                            .username("admin")
//                            .password("{noop}"+"1111")
                            .password(passwordEncoder().encode("1111"))
                            .roles("ADMIN", "USER")
                            .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/login", "/", "/info", "/any", "/accounts/new").permitAll()       //권한이 없어도 접근 가능한 uri
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin(form -> form.loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/error"));

        return http.build();
    }
}
