package uasz.sn.gestionscolarite.controller;

import uasz.sn.gestionscolarite.model.Utilisateur;
import uasz.sn.gestionscolarite.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Vue de la page de connexion
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody Utilisateur utilisateur) {
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(utilisateur.getPassword()));
        userDetailsService.save(utilisateur);
        return "redirect:/login"; // Redirection après enregistrement
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout"; // Redirection après déconnexion
    }
}
