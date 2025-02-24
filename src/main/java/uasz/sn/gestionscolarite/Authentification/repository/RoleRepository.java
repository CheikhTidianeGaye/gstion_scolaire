package uasz.sn.gestionscolarite.Authentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uasz.sn.gestionscolarite.Authentification.modele.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

    // Recherche un rôle par son nom
    @Query("SELECT r FROM Role r WHERE r.role = :role")
    Role findRoleByRole(@Param("role") String role);
}
