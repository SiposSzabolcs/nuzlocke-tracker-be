package com.example.nuzlocke_tracker_api.trainer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @ElementCollection
    @CollectionTable(name = "trainer_pokemon_party", joinColumns = @JoinColumn(name = "trainer_id"))
    @Column(name = "pokemon_name")
    private List<String> pokemonParty = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "trainer_pokemon_box", joinColumns = @JoinColumn(name = "trainer_id"))
    @Column(name = "pokemon_name")
    private List<String> pokemonBox = new ArrayList<>();

    private Integer userId;


}
