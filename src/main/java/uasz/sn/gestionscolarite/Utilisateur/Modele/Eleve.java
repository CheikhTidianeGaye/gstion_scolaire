package uasz.sn.gestionscolarite.Utilisateur.Modele;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.gestionscolarite.Authentification.modele.Utilisateur;


import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "eleve")
public class Eleve extends Utilisateur {

    @Column(unique = true, nullable = false)
    private String matricule;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;



    private String responsableLegal; // Contact du parent/tuteur
    private boolean active = true; // Valeur par défaut
    private boolean archive = true; // Valeur par défaut
}
