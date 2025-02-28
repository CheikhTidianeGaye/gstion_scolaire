package uasz.sn.gestionscolarite.Utilisateur.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Administrateur;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    Administrateur findByEmailProfessionnel(String emailProfessionnel);
}
