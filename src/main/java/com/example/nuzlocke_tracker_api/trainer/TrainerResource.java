package com.example.nuzlocke_tracker_api.trainer;

import com.example.nuzlocke_tracker_api.global.ApiResponse;
import com.example.nuzlocke_tracker_api.pokemon.PokemonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
public class TrainerResource {
    private final TrainerService trainerService;

    @GetMapping
    public ResponseEntity<Page<Trainer>> getTrainers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(trainerService.getAllTrainers(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTrainer(@PathVariable(value = "id") Integer id){
        try {
            return ResponseEntity.ok().body(trainerService.getTrainer(id));
        } catch(Exception e){
            ApiResponse response = new ApiResponse("An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTrainersByUserId(@PathVariable(value = "userId") Integer userId) {
        try {
            List<Trainer> trainers = trainerService.getTrainersByUserId(userId);
            return ResponseEntity.ok(trainers);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        return ResponseEntity.created(URI.create("/trainers/" + trainer.getId())).body(trainerService.createTrainer(trainer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainer(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok().body(trainerService.deleteTrainerById(id));
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/{id}/pokemon")
    public ResponseEntity<?> addPokemon(@PathVariable (value = "id") Integer id, @RequestBody PokemonDTO pokemonDTO) {
        try {
            Trainer updatedTrainer = trainerService.addPokemon(id, pokemonDTO.getPokemonName());
            return ResponseEntity.ok(updatedTrainer);
        }catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}/pokemon")
    public ResponseEntity<?> removePokemon(@PathVariable (value = "id") Integer id, @RequestBody PokemonDTO pokemonDTO){
        try {
            Trainer updatedTrainer = trainerService.removePokemon(id,pokemonDTO.getPokemonName());
            return ResponseEntity.ok(updatedTrainer);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/{id}/routes")
    public ResponseEntity<?> addRouteIds(@PathVariable Integer id, @RequestBody List<Integer> routeIds) {
        try {
            Trainer updatedTrainer = trainerService.addRouteIds(id, routeIds);
            return ResponseEntity.ok(updatedTrainer);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}/routes/{routeId}")
    public ResponseEntity<TrainerDTO> removeRouteId(@PathVariable Integer id, @PathVariable Integer routeId) {
        Trainer trainer = trainerService.removeRouteId(id, routeId);

        TrainerDTO trainerDTO = new TrainerDTO(
                trainer.getId(),
                trainer.getName(),
                new ArrayList<>(trainer.getPokemonBox()),
                new ArrayList<>(trainer.getRouteIds())
        );

        return ResponseEntity.ok(trainerDTO);
    }

    @PutMapping("/{id}/pokemon/clear")
    public ResponseEntity<?> clearPokemonBox(@PathVariable(value = "id") Integer id) {
        try {
            Trainer updatedTrainer = trainerService.clearPokemonBox(id);
            return ResponseEntity.ok(updatedTrainer);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}/pokemon/evolve")
    public ResponseEntity<?> evolvePokemon(@PathVariable(value = "id") Integer id, @RequestBody PokemonDTO pokemonDTO) {
        try {
            Trainer updatedTrainer = trainerService.evolvePokemon(id, pokemonDTO.getPokemonName(), pokemonDTO.getEvolvedPokemonName());
            return ResponseEntity.ok(updatedTrainer);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
