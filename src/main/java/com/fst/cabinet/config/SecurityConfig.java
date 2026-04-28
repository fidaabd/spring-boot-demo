package com.fst.cabinet.config;

import com.fst.cabinet.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UtilisateurRepository utilisateurRepository;

    // 🔐 Charger les utilisateurs depuis la base MySQL
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var user = utilisateurRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole()) // ADMIN / MEDECIN / SECRETAIRE
                    .build();
        };
    }

    // 🔑 Cryptage mot de passe (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🛡️ Règles de sécurité + accès par rôles
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // fichiers statiques accessibles sans login
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // accès ADMIN uniquement
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // accès MEDECIN ou ADMIN
                        .requestMatchers("/medecin/**").hasAnyRole("ADMIN", "MEDECIN")

                        // accès SECRETAIRE ou ADMIN
                        .requestMatchers("/patients/**", "/medecins/**")
                        .hasAnyRole("ADMIN", "SECRETAIRE")

                        // dashboard pour tous les utilisateurs connectés
                        .requestMatchers("/", "/dashboard").authenticated()

                        // tout le reste nécessite login
                        .anyRequest().authenticated()
                )

                // page login personnalisée
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )

                // logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}