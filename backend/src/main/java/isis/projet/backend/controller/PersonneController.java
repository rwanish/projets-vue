package isis.projet.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import isis.projet.backend.dao.PersonneRepository;
import isis.projet.backend.entity.Personne;
import java.util.List;


import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.stream.Collectors;

/**
 * DTO for {@link isis.projet.backend.entity.Personne}
 */


@RestController
@RequestMapping("/api/personnes/{id}")
public class PersonneController {
    private final PersonneRepository personneRepository;

    public PersonneController(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @GetMapping
    public List<PersonneDTO> getAllPersonnes() {
        return personneRepository.findAll()
                .stream()
                .map(personne -> new PersonneDTO(
                        personne.getMatricule(),
                        personne.getNom(),
                        personne.getPrenom()))
                .collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class PersonneDTO {
    private Integer matricule;
    private String nom;
    private String prenom;
}

