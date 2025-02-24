package uasz.sn.gestionscolarite.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String niveau;

    @OneToMany(mappedBy = "classe")
    private List<Eleve> eleves;

    // Getters et Setters
}
