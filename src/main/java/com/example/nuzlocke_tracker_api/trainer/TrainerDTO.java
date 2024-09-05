package com.example.nuzlocke_tracker_api.trainer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO {
    private Integer id;
    private String name;
    private List<String> pokemonBox;
    private List<String> routeIds;
}
