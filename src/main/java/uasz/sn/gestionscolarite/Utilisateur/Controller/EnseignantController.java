package uasz.sn.gestionscolarite.Utilisateur.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uasz.sn.gestionscolarite.Authentification.modele.Role;
import uasz.sn.gestionscolarite.Authentification.modele.Utilisateur;
import uasz.sn.gestionscolarite.Authentification.service.UtilisateurService;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Enseignant;
import uasz.sn.gestionscolarite.Utilisateur.Service.EnseignantService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EnseignantController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EnseignantService enseignantService;

    private PasswordEncoder passwordEncoder;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/Enseignant/Accueil", method = RequestMethod.GET)
    public String accueil_Enseignant(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_enseignant";
    }

    @RequestMapping(value = "/SuperAdministrateur/Accueil", method = RequestMethod.GET)
    public String accueil_SuperAdministrateur(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_SuperAdministrateur";
    }

    @RequestMapping(value = "/SuperAdministrateur/ajouterEnseignant", method = RequestMethod.POST)
    public String ajouter_Enseignant(Enseignant enseignant) {
        String password = passwordEncoder().encode("Passer123");
        enseignant.setPassword(password);
        enseignant.setDateCreation(new Date());

        Role role = utilisateurService.ajouter_Role(new Role("Enseignant"));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        enseignant.setRoles(roles);

        enseignantService.ajouter(enseignant);

        return "redirect:/SuperAdministrateur/Administrateur";
    }

    @RequestMapping(value = "/SuperAdministrateur/modifierEnseignant", method = RequestMethod.POST)
    public String modifier_Enseignant(Enseignant enseignant) {
        Enseignant ens_modif = enseignantService.rechercherParId(enseignant.getId());
        ens_modif.setMatricule(enseignant.getMatricule());
        ens_modif.setNom(enseignant.getNom());
        ens_modif.setPrenom(enseignant.getPrenom());
        ens_modif.setSpecialite(enseignant.getSpecialite());
        ens_modif.setMatricule(enseignant.getMatricule());

        enseignantService.modifier(ens_modif);

        return "redirect:/SuperAdministrateur/Administrateur";
    }

    @RequestMapping(value = "/SuperAdministrateur/activerEnseignant", method = RequestMethod.POST)
    public String activer_Enseignant(Long id) {
        enseignantService.activer(id);
        return "redirect:/SuperAdministrateur/Administrateur";
    }

    @RequestMapping(value = "/SuperAdministrateur/archiverEnseignant", method = RequestMethod.POST)
    public String archiver_Enseignant(Long id) {
        enseignantService.archiver(id);
        return "redirect:/SuperAdministrateur/Administrateur";
    }
}
