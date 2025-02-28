package uasz.sn.gestionscolarite.Utilisateur.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uasz.sn.gestionscolarite.Utilisateur.Modele.Eleve;
import uasz.sn.gestionscolarite.Utilisateur.Repository.EleveRepository;
import uasz.sn.gestionscolarite.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EleveService {

    private final EleveRepository eleveRepository;

    public Eleve ajouter(Eleve eleve) {
        return eleveRepository.save(eleve);
    }

    public List<Eleve> liste() {
        return eleveRepository.findAll();
    }

    public Eleve rechercherParId(Long id) {
        return eleveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Élève avec ID " + id + " non trouvé"));
    }

    public Eleve modifier(Eleve eleve) {
        if (!eleveRepository.existsById(eleve.getId())) {
            throw new ResourceNotFoundException("Élève avec ID " + eleve.getId() + " non trouvé");
        }
        return eleveRepository.save(eleve);
    }

    public void supprimer(Long id) {
        Eleve eleve = rechercherParId(id);
        eleveRepository.delete(eleve);
    }

    public void active(Long id) {
        Eleve eleve = rechercherParId(id);
        eleve.setActive(!eleve.isActive()); // Basculer l'état actif/inactif
        eleveRepository.save(eleve);
    }

    public void archive(Long id) {
        Eleve eleve = rechercherParId(id);
        eleve.setArchive(!eleve.isArchive()); // Basculer l'état archivé/non archivé
        eleveRepository.save(eleve);
    }


}
