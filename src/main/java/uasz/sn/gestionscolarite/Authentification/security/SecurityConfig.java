package uasz.sn.gestionscolarite.Authentification.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] FOR_PERMANENT = {"/Eleve/**"};
    private static final String[] FOR_VACATAIRE = {"/Enseignant/**"};
    private static final String[] FOR_CHEFDEPARTEMENT = {"/Administrtion/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/js/**", "/css/**").permitAll()
                        .requestMatchers("/login**", "/logout**", "/details_maquette_classe**","/succes**").permitAll()
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/apiDTO/**").permitAll()
                        .requestMatchers(FOR_PERMANENT).hasRole("Permanent")
                        .requestMatchers(FOR_VACATAIRE).hasRole("Vacataire")
                        .requestMatchers(FOR_CHEFDEPARTEMENT).hasRole("ChefDepartement")
                        .requestMatchers("/suppMaquette/**").hasRole("ChefDepartement")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .successForwardUrl("/")
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/api/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/apiDTO/**"))
                );

        return http.build();
    }

}