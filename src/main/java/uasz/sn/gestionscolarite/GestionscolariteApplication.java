package uasz.sn.gestionscolarite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uasz.sn.gestionscolarite.Authentification.modele.Role;
import uasz.sn.gestionscolarite.Authentification.service.UtilisateurService;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Administrateur;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Eleve;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Enseignant;
import uasz.sn.gestionscolarite.Utilisateur.Service.AdministrateurService;
import uasz.sn.gestionscolarite.Utilisateur.Service.EleveService;
import uasz.sn.gestionscolarite.Utilisateur.Service.EnseignantService;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class GestionscolariteApplication implements CommandLineRunner {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private EnseignantService enseignantService;

	@Autowired
	private EleveService eleveService;

	@Autowired
	private AdministrateurService administrateurService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestionscolariteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🔹 Initialisation des utilisateurs...");

		// Création des rôles
		Role eleve = utilisateurService.ajouter_Role(new Role("Eleve"));
		Role enseignant = utilisateurService.ajouter_Role(new Role("Enseignant"));
		Role admin = utilisateurService.ajouter_Role(new Role("Administrateur"));

		String password = passwordEncoder.encode("Passer123");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


		// 🔹 Création d'un Enseignant
		Enseignant user_1 = new Enseignant();
		user_1.setNom("DIOP");
		user_1.setPrenom("Ibrahima");
		user_1.setUsername("ibraidiop@uasz.sn");
		user_1.setPassword(password);
		user_1.setDateCreation(new Date());
		user_1.setActive(true);
		user_1.setSpecialite("Web Sémantique");
		user_1.setMatricule("ID2024");

		enseignantService.ajouter(user_1);
		utilisateurService.ajouter_UtilisateurRoles(user_1, enseignant);
		System.out.println("✅ Enseignant ajouté : " + user_1.getUsername());

		// 🔹 Création d'un Élève
		Eleve user_2 = new Eleve();
		user_2.setNom("NDIAYE");
		user_2.setPrenom("Aissatou");
		user_2.setUsername("aissatou.ndiaye@uasz.sn");
		user_2.setPassword(password);
		user_2.setDateCreation(new Date());
		user_2.setDateNaissance(dateFormat.parse("08/02/2001"));
		user_2.setMatricule("2345G");

		eleveService.ajouter(user_2);
		utilisateurService.ajouter_UtilisateurRoles(user_2, eleve);
		System.out.println("✅ Élève ajouté : " + user_2.getUsername());

		// 🔹 Création d'un Administrateur
		Administrateur user_3 = new Administrateur();
		user_3.setNom("DIAGNE");
		user_3.setPrenom("Serigne");
		user_3.setUsername("ssndiagne@uasz.sn");
		user_3.setPassword(password);
		user_3.setDateCreation(new Date());
		user_3.setActive(true);
		user_3.setPoste("Responsable Base de Données");
		user_3.setEmailProfessionnel("SCHOOL@gmail.com");
		user_3.setTelephone("770172887");

		administrateurService.ajouter(user_3);
		utilisateurService.ajouter_UtilisateurRoles(user_3, admin);
		System.out.println("✅ Administrateur ajouté : " + user_3.getUsername());

		System.out.println("🎉 Initialisation terminée avec succès !");
	}
}
