package isis.projet.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import isis.projet.backend.dao.ProjetRepository;
import isis.projet.backend.entity.Projet;
import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;

/**
 * DTO for {@link isis.projet.backend.entity.Projet}
 */

@RestController
@RequestMapping("/api/projets")
public class ProjetController {
    private final ProjetRepository projetRepository;

    public ProjetController(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @GetMapping
    public List<ProjetDTO> getAllProjets() {
        return projetRepository.findAll()
                .stream()
                .map(projet -> new ProjetDTO(
                        projet.getId(),
                        projet.getNom(),
                        projet.getDebut().toString(),
                        projet.getFin().toString()))
                .collect(Collectors.toList());
    }
    @PostMapping
    public ResponseEntity<ProjetDTO> addProjet(@RequestBody ProjetDTO projetDTO) {
        Projet projet = new Projet();
        projet.setNom(projetDTO.getNom());

        Projet savedProjet = projetRepository.save(projet);
        ProjetDTO savedProjetDTO = new ProjetDTO(savedProjet.getId(), savedProjet.getNom(), savedProjet.getDebut().toString(), savedProjet.getFin().toString() );

        return new ResponseEntity<>(savedProjetDTO, HttpStatus.CREATED);
    }
}

@Data
@AllArgsConstructor
class ProjetDTO {
    private Integer id;
    private String nom;
    private String debut;
    private String fin;
}