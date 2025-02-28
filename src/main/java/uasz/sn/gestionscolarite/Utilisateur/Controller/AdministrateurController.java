package uasz.sn.gestionscolarite.Utilisateur.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.gestionscolarite.Authentification.modele.Utilisateur;
import uasz.sn.gestionscolarite.Authentification.service.UtilisateurService;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Eleve;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Enseignant;
import uasz.sn.gestionscolarite.Utilisateur.Service.EleveService;
import uasz.sn.gestionscolarite.Utilisateur.Service.EnseignantService;

import java.security.Principal;
import java.util.List;

@Controller
public class AdministrateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private EleveService eleveService;
    private PasswordEncoder passwordEncoder;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/Administrateur/Accueil", method = RequestMethod.GET)
    public String accueil_Administrateur(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_Administrateur";
    }

    @RequestMapping(value = "/Administrateur/Enseignant", method = RequestMethod.GET)
    public String Administrateur_Enseignant(Model model, Principal principal) {
        List<Enseignant> enseignants = enseignantService.liste();
        model.addAttribute("enseignants", enseignants);
        // Récupération des informations de l'Enseignant connecté
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));

        return "Administrateur_Enseignant";
    }
    @RequestMapping(value = "/Administrateur/Eleve", method = RequestMethod.GET)
    public String Administrateur_Eleve(Model model, Principal principal) {
        // Récupération de la liste des élèves
        List<Eleve> eleves = eleveService.liste();
        model.addAttribute("eleves", eleves);
        // Récupération des informations de l'Eleve connecté
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));

        return "Administrateur_Eleve";
    }
}
