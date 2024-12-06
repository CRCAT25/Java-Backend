package com.rs.retailstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
//        Dùng để encode password thay vì tải Spring Boot CLI
//        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println("Hashed Password: " + hashedPassword);

        // Tạo user mới là "user"
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$jauzj7Dap4207RyRPQT7NeXXCpxHt8d3gVSu.JXUxzI.Rxtt50RUy")
                .roles("USER")
                .build();

        // Tạo user mới là "admin"
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$WHhFJ0VIwMc86aaIiG4uHuReoDsCMlBA5zlTBn9MBT85E30RnIKD2")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
