package uasz.sn.gestionscolarite.Utilisateur.Modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uasz.sn.gestionscolarite.Authentification.modele.Utilisateur;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enseignant")
public class Enseignant extends Utilisateur {

    @Column(unique = true, nullable = false)

    private String matricule;

    private String specialite;

    private boolean active = true; // Valeur par défaut
    private boolean archive = true; // Valeur par défaut
}
