package com.spring.db.spscdemo1.config;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")    // ✅ FIXED: Use hasAuthority
                .requestMatchers("/manager/**").hasAuthority("ROLE_MANAGER") // ✅ FIXED: Use hasAuthority
                .requestMatchers("/user/**").hasAuthority("ROLE_USER")      // ✅ FIXED: Use hasAuthority
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login") // http login page
                .loginProcessingUrl("/login")
                .successHandler(this::customSuccessHandler) // ✅ Custom redirect handler
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success")
                .permitAll()
            );

        return http.build();
    }

    // 🔥 Improved Custom Success Handler with Correct Role Priority
    private void customSuccessHandler(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException {
        String redirectUrl = "/public/home"; // Default fallback

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            if (role.equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/dashboard";
                break;
            } else if (role.equals("ROLE_MANAGER")) {
                redirectUrl = "/manager/home";
                break;
            } else if (role.equals("ROLE_USER")) {
                redirectUrl = "/user/profile";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
