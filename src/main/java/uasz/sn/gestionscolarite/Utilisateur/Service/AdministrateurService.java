package uasz.sn.gestionscolarite.Utilisateur.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Administrateur;
import uasz.sn.gestionscolarite.Utilisateur.Repository.AdministrateurRepository;
import uasz.sn.gestionscolarite.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdministrateurService {

    private final AdministrateurRepository administrateurRepository;

    public Administrateur ajouter(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public List<Administrateur> liste() {
        return administrateurRepository.findAll();
    }

    public Administrateur rechercherParId(Long id) {
        return administrateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrateur avec ID " + id + " non trouvé"));
    }

    public Administrateur modifier(Administrateur administrateur) {
        if (!administrateurRepository.existsById(administrateur.getId())) {
            throw new ResourceNotFoundException("Administrateur avec ID " + administrateur.getId() + " non trouvé");
        }
        return administrateurRepository.save(administrateur);
    }

    public void supprimer(Long id) {
        Administrateur administrateur = rechercherParId(id);
        administrateurRepository.delete(administrateur);
    }

    public void activer(Long id) {
        Administrateur administrateur = rechercherParId(id);
        administrateur.setActive(!administrateur.isActive()); // Bascule entre actif/inactif
        administrateurRepository.save(administrateur);
    }

    public void archiver(Long id) {
        Administrateur administrateur = rechercherParId(id);
        administrateur.setArchiver(!administrateur.isArchiver()); // Bascule entre archivé/non archivé
        administrateurRepository.save(administrateur);
    }
}
