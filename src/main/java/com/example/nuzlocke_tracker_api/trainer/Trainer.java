package com.example.nuzlocke_tracker_api.trainer;

import com.example.nuzlocke_tracker_api.pokemon.Pokemon;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private ArrayList<Pokemon> pokemonParty;
    private ArrayList<Pokemon> pokemonBox;
    private Integer userId;


}
