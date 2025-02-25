package uasz.sn.gestionscolarite.Utilisateur.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.Gestion_Enseignement.Utilisateur.Modele.Vacataire;

@Repository
public interface VacataireRepository extends JpaRepository<Vacataire, Long> {
}
