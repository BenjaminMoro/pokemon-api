package com.challenge.jemer.pokemon_api.services.pokemon;

import com.challenge.jemer.pokemon_api.dtos.PokemonResponseDTO;
import com.challenge.jemer.pokemon_api.entities.PokemonHistoryEntity;
import com.challenge.jemer.pokemon_api.entities.PokemonType;
import com.challenge.jemer.pokemon_api.repositories.PokemonRepository;
import com.challenge.jemer.pokemon_api.services.webClient.PokemonWebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonWebClientService pokemonWebClientService;
    private final PokemonRepository pokemonRepository;

    public PokemonResponseDTO getPokemonByName(String name) {

        PokemonResponseDTO responseDTO = pokemonWebClientService.getPokemonByName(name);

        PokemonHistoryEntity pokemonHistoryEntity = PokemonHistoryEntity.builder()
                .name(responseDTO.getName())
                .imageUrl(responseDTO.getImageUrl())
                .types(responseDTO.getTypes().stream()
                        .map(type -> PokemonType.builder()
                                .name(type)
                                .build())
                        .toList())
                .searchDate(java.time.LocalDateTime.now())
                .build();

        pokemonRepository.save(pokemonHistoryEntity);


        return responseDTO;
    }
}
