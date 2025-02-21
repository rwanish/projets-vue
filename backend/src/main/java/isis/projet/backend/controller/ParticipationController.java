package isis.projet.backend.controller;

import isis.projet.backend.service.ParticipationProjet;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/gestion/participation")
public class ParticipationController {

    private final ParticipationProjet participationService;
    private final ModelMapper mapper;

    public ParticipationController(ParticipationProjet participationService, ModelMapper mapper) {
        this.participationService = participationService;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity<?>enregistrerParticipation(@RequestBody ParticipationDTO participationDTO){
        try {
            // On appelle le service métier
            var participation = participationService.enregistrerParticipation(
                    participationDTO.getMatricule(),
                    participationDTO.getCodeProjet(),
                    participationDTO.getRole(),
                    participationDTO.getPourcentage()
            );

            // On renvoie la participation créée sous la forme d'un DTO
            var body = mapper.map(participation, ParticipationDTO.class);
            return ResponseEntity.ok(body);
            // En cas d'erreur, on renvoie des informations sur l'erreur pour le frontend
        } catch (NoSuchElementException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(new ApiErrorDTO("Cette personne participe déjà au projet"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiErrorDTO("Une erreur est survenue : " + e.getMessage()));
        }
    }
}