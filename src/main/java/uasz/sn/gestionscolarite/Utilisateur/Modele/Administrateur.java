package uasz.sn.gestionscolarite.Utilisateur.Modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.gestionscolarite.Authentification.modele.Utilisateur;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "administrateur")
public class Administrateur extends Utilisateur {

    @Column(nullable = false)
    private String poste; // Fonction occupée (Directeur, Secrétaire...)

    @Column(unique = true, nullable = false)
    private String emailProfessionnel; // Email utilisé pour le travail

    @Column(unique = true, nullable = false)
    private String telephone; // Contact professionnel
    private boolean activer = true; // Valeur par défaut
    private boolean archiver = true; // Valeur par défaut
}
