package com.rs.retailstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
//        Dùng để encode password thay vì tải Spring Boot CLI
        String hashedPassword = BCrypt.hashpw("12345", BCrypt.gensalt());
        System.out.println("Hashed Password: " + hashedPassword);

        // Tạo user mới là "user"
        UserDetails user = User.builder()
                .username("user")
                .password("$2a$10$UsqFfE2UvnAgNgWAqQnaxO54EiqpyLehBEHR3MuVMtkCzBdcTNeLS")
                .roles("USER")
                .build();

        // Tạo user mới là "admin"
        UserDetails admin = User.builder()
                .username("admin")
                .password("$2a$10$UsqFfE2UvnAgNgWAqQnaxO54EiqpyLehBEHR3MuVMtkCzBdcTNeLS")
                .roles("USER", "ADMIN")
                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
