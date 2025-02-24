package uasz.sn.gestionscolarite.Authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uasz.sn.gestionscolarite.Authentification.modele.Utilisateur;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT u FROM Utilisateur u WHERE u.username = :username")
    Utilisateur findUtilisateurByUsername(@Param("username") String username);

    // Recherche un utilisateur par son identifiant unique (id)
    @Query("SELECT u FROM Utilisateur u WHERE u.id = :id")
    Utilisateur findUtilisateurById(@Param("id") Long id);

    // Recherche un utilisateur par son état actif (active)
    @Query("SELECT u FROM Utilisateur u WHERE u.active = :active")
    List<Utilisateur> findAllByActive(@Param("active") boolean active);
}
