package uasz.sn.gestionscolarite.Utilisateur.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uasz.sn.Gestion_Enseignement.Utilisateur.Modele.Etudiant;
import uasz.sn.Gestion_Enseignement.Utilisateur.Repository.EtudiantRepository;


import java.util.List;

@Service
@Transactional
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant ajouter(Etudiant etudiant){
        return  etudiantRepository.save(etudiant);
    }

    public List<Etudiant> lister (){
        return etudiantRepository.findAll();
    }

    public Etudiant rechercher(Long id){
        return etudiantRepository.findById(id).get();
    }

    public Etudiant modifier(Etudiant etudiant){
        return  etudiantRepository.save(etudiant);
    }

    public void supprimer(Long id){
        etudiantRepository.deleteById(id);
    }
}

