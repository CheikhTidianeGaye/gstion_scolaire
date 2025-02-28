package uasz.sn.gestionscolarite.Authentification.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] FOR_ELEVE = {"/Eleve/**"};
    private static final String[] FOR_ENSEIGNANT = {"/Enseignant/**"};
    private static final String[] FOR_ADMINISTRATEUR = {"/Administrateur/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/js/**", "/css/**").permitAll()
                        .requestMatchers("/login**", "/logout**").permitAll()
                        .requestMatchers(FOR_ELEVE).hasRole("Eleve")
                        .requestMatchers(FOR_ENSEIGNANT).hasRole("Enseignant")
                        .requestMatchers(FOR_ADMINISTRATEUR).hasRole("Administrateur")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .successForwardUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}
