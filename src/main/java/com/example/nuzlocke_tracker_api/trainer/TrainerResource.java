package com.example.nuzlocke_tracker_api.trainer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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

    @PostMapping
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
        return ResponseEntity.created(URI.create("/trainers/" + trainer.getId())).body(trainerService.createTrainer(trainer));
    }
}
