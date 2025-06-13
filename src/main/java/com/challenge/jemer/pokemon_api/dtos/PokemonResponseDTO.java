package com.challenge.jemer.pokemon_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonResponseDTO {

    private String name;
    private String imageUrl;
    private List<String> types;
}
