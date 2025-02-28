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
import uasz.sn.gestionscolarite.Utilisateur.Modele.Eleve;
import uasz.sn.gestionscolarite.Utilisateur.Service.EleveService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EleveController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EleveService eleveService;

    private PasswordEncoder passwordEncoder;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value = "/Eleve/Accueil", method = RequestMethod.GET)
    public String accueil_Eleve(Model model, Principal principal) {
        Utilisateur utilisateur = utilisateurService.rechercher_Utilisateur(principal.getName());
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom().charAt(0));
        return "template_eleve";
    }


    @RequestMapping(value = "/Administrateur/ajouterEleve", method = RequestMethod.POST)
    public String ajouter_Eleve(Eleve eleve) {
        String password = passwordEncoder().encode("Passer123");
        eleve.setPassword(password);
        eleve.setDateCreation(new Date());

        Role role = utilisateurService.ajouter_Role(new Role("Eleve"));
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        eleve.setRoles(roles);

        eleveService.ajouter(eleve);

        return "redirect:/Administrateur/Eleve";
    }

    @RequestMapping(value = "/Administrateur/modifierEleve", method = RequestMethod.POST)
    public String modifier_Eleve(Eleve eleve) {
        Eleve eleveModif = eleveService.rechercherParId(eleve.getId());
        eleveModif.setMatricule(eleve.getMatricule());
        eleveModif.setNom(eleve.getNom());
        eleveModif.setPrenom(eleve.getPrenom());
        eleveModif.setDateNaissance(eleve.getDateNaissance());

        eleveService.modifier(eleveModif);

        return "redirect:/Administrateur/Eleve";
    }

    @RequestMapping(value = "/Administrateur/activerEleve", method = RequestMethod.POST)
    public String activer_Eleve(Long id) {
        eleveService.active(id);
        return "redirect:/Administrateur/eleve";
    }

    @RequestMapping(value = "/Administrateur/archiverEleve", method = RequestMethod.POST)
    public String archiver_Eleve(Long id) {
        eleveService.archive(id);
        return "redirect:/Administrateur/eleve";
    }
}
