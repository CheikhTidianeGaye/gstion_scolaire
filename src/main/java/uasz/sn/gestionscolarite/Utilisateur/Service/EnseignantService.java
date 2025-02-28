package uasz.sn.gestionscolarite.Utilisateur.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Enseignant;
import uasz.sn.gestionscolarite.Utilisateur.Repository.EnseignantRepository;
import uasz.sn.gestionscolarite.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EnseignantService {

    private final EnseignantRepository enseignantRepository;

    public Enseignant ajouter(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public List<Enseignant> liste() {
        return enseignantRepository.findAll();
    }

    public Enseignant rechercherParId(Long id) {
        return enseignantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant avec ID " + id + " non trouvé"));
    }

    public Enseignant modifier(Enseignant enseignant) {
        if (!enseignantRepository.existsById(enseignant.getId())) {
            throw new ResourceNotFoundException("Enseignant avec ID " + enseignant.getId() + " non trouvé");
        }
        return enseignantRepository.save(enseignant);
    }

    public void active(Long id) {
        Enseignant enseignant = rechercherParId(id);
        enseignant.setActive(!enseignant.isActive()); // Inverse l'état
        enseignantRepository.save(enseignant);
    }

    public void archive(Long id) {
        Enseignant enseignant = rechercherParId(id);
        enseignant.setArchive(!enseignant.isArchive()); // Inverse l'état
        enseignantRepository.save(enseignant);
    }


}
