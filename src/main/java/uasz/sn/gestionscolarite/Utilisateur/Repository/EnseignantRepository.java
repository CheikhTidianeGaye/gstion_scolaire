package uasz.sn.gestionscolarite.Utilisateur.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Enseignant;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Enseignant findByMatricule(String matricule);
}
