package com.example.nuzlocke_tracker_api.pokemon;

import com.example.nuzlocke_tracker_api.global.CustomExceptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PokemonService {

    public void validatePokemon(Pokemon pokemon) {
        if (!checkPokemon(pokemon.getName())) {
            throw new CustomExceptions.NonValidPokemonException(pokemon.getName());
        }
    }

    public static boolean checkPokemon(String pokemonName) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + pokemonName.toLowerCase()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return response.statusCode() != 404;
    }
}
