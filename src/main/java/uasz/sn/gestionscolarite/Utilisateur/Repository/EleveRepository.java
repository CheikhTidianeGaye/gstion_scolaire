package uasz.sn.gestionscolarite.Utilisateur.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Eleve;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
    Eleve findByMatricule(String matricule);
}
