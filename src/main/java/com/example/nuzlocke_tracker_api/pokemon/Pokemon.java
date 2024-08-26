package com.example.nuzlocke_tracker_api.pokemon;

import com.example.nuzlocke_tracker_api.global.CustomExceptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pokemon {
    String name;

    public Pokemon(String name) {
        if (PokemonService.checkPokemon(name)) {
            this.name = name;
        } else {
            throw new CustomExceptions.NonValidPokemonException(name);
        }
    }
}
