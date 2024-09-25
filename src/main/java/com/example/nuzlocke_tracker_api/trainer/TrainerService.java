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

import java.util.ArrayList;
import java.util.List;

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

    public List<Trainer> getTrainersByUserId(Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException(userId));

        return trainerRepository.findByUserId(userId);
    }

    public Trainer createTrainer(Trainer trainer){
        userRepository.findById(trainer.getUserId())
                .orElseThrow(() -> new CustomExceptions.UserNotFoundException(trainer.getUserId()));
        return trainerRepository.save(trainer);
    }

    public TrainerDTO deleteTrainerById(Integer id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        trainerRepository.delete(trainer);
        return new TrainerDTO(
                trainer.getId(),
                trainer.getName(),
                new ArrayList<>(trainer.getPokemonBox()),
                new ArrayList<>(trainer.getRouteIds()),
                trainer.getGame()
        );
    }

    public Trainer addPokemon(Integer id, String pokemonName) throws Exception {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        pokemonService.validatePokemon(pokemonName);

        trainer.getPokemonBox().add(pokemonName);

        return trainerRepository.save(trainer);
    }

    public Trainer removePokemon(Integer id, String pokemonName) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        if (trainer.getPokemonBox().contains(pokemonName)) {
            trainer.getPokemonBox().remove(pokemonName);
        } else {
            throw new CustomExceptions.PokemonNotInPartyException(pokemonName);
        }

        return trainerRepository.save(trainer);
    }

    public Trainer addRouteIds(Integer id, List<Integer> routeIds) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        trainer.getRouteIds().addAll(routeIds);
        return trainerRepository.save(trainer);
    }

    public Trainer removeRouteId(Integer trainerId, Integer routeId) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(trainerId));

        if (trainer.getRouteIds().contains(routeId)) {
            trainer.getRouteIds().remove(routeId);
        } else {
            throw new CustomExceptions.RouteIdNotFoundException(routeId);
        }

        return trainerRepository.save(trainer);
    }

    public Trainer clearPokemonBox(Integer id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(id));

        trainer.getPokemonBox().clear();

        return trainerRepository.save(trainer);
    }

    public Trainer evolvePokemon(Integer trainerId, String pokemonName, String evolvedPokemonName) throws Exception {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new CustomExceptions.TrainerNotFoundException(trainerId));

        List<String> pokemonBox = trainer.getPokemonBox();
        int index = pokemonBox.indexOf(pokemonName);

        if (index == -1) {
            throw new CustomExceptions.PokemonNotInPartyException(pokemonName);
        }

        pokemonBox.set(index, evolvedPokemonName);

        return trainerRepository.save(trainer);
    }



}
