package com.fst.cabinet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("fida123")
                .roles("ADMIN")
                .build();

        UserDetails secretaire = User.builder()
                .username("secretaire")
                .password("fida123")
                .roles("SECRETAIRE")
                .build();

        UserDetails medecin = User.builder()
                .username("docteur1")
                .password("fida123")
                .roles("MEDECIN")
                .build();

        return new InMemoryUserDetailsManager(admin, secretaire, medecin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Ressources statiques
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // Pages publiques sans login
                        .requestMatchers("/public/**").permitAll()

                        // Dashboards par rôle (ajout Sprint 3)
                        .requestMatchers("/dashboard/admin").hasRole("ADMIN")
                        .requestMatchers("/dashboard/medecin").hasAnyRole("ADMIN", "MEDECIN")
                        .requestMatchers("/dashboard/secretaire").hasAnyRole("ADMIN", "SECRETAIRE")

                        // Anciennes règles conservées
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/medecin/**").hasAnyRole("ADMIN", "MEDECIN")

                        // Tout le reste nécessite d'être connecté
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true) // true ajouté Sprint 3
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}