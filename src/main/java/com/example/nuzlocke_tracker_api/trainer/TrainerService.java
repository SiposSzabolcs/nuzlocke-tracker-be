package com.example.nuzlocke_tracker_api.trainer;

import com.example.nuzlocke_tracker_api.Security.user.UserRepository;
import com.example.nuzlocke_tracker_api.global.CustomExceptions;
import com.example.nuzlocke_tracker_api.pokemon.PokemonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final PokemonService pokemonService;

    public Page<Trainer> getAllTrainers(int page, int size) {
        return trainerRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Trainer getTrainer(Integer id) {

        return trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));
    }

    public Trainer createTrainer(Trainer trainer){
        userRepository.findById(trainer.getUserId())
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException(trainer.getUserId()));
        return trainerRepository.save(trainer);
    }

    public Trainer deleteTrainerById(Integer id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        trainerRepository.delete(trainer);
        return trainer;
    }

    public Trainer addPokemon(Integer id, String pokemonName) throws Exception {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        pokemonService.validatePokemon(pokemonName);

        if (trainer.getPokemonParty().size() >= 6) {
            trainer.getPokemonBox().add(pokemonName);
        } else {
            trainer.getPokemonParty().add(pokemonName);
        }
        return trainerRepository.save(trainer);
    }
}
