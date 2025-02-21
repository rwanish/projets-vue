package isis.projet.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

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
                        projet.getNom()))
                .collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class ProjetDTO {
    private Integer id;
    private String nom;
}