package com.challenge.jemer.pokemon_api.services.webClient;

import com.challenge.jemer.pokemon_api.dtos.PokemonResponseDTO;
import com.challenge.jemer.pokemon_api.dtos.exceptions.ResourceNotFoundException;
import com.challenge.jemer.pokemon_api.dtos.records.PokemonApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonWebClientService {

    private final WebClient webClient;

    public PokemonResponseDTO getPokemonByName(String name) {

        PokemonApiResponse response = webClient.get()
                .uri("pokemon/{name}", name)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        clientResponse -> {
                            if (clientResponse.statusCode().value() == 404) {
                                return Mono.error(new ResourceNotFoundException("No se encontro el pokemon con nombre: " + name));
                            }
                            return Mono.error(new RuntimeException("Error al consultar a PokeAPI"));
                        }
                )
                .bodyToMono(PokemonApiResponse.class)
                .block();

        return PokemonResponseDTO.builder()
                .name(response.name())
                .imageUrl(response.sprites().front_default())
                .types(response.types().stream()
                        .map(t -> t.type().name())
                        .toList())
                .build();
    }
}