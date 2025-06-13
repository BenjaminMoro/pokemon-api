package com.challenge.jemer.pokemon_api.controllers;

import com.challenge.jemer.pokemon_api.dtos.PokemonResponseDTO;
import com.challenge.jemer.pokemon_api.services.pokemon.PokemonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/{name}")

    @Operation(
            summary = "Obtener pokemon por nombre",
            description = "Devuelve el nombre, la imagen y los tipos del pokemon especificado por nombre. Usa la PokeAPI como fuente de datos externa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pokemon encontrado y datos devueltos correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontro un pokemon con el nombre especificado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<PokemonResponseDTO> getPokemonByName(
            @Parameter(
                    description = "Nombre del pokemon a buscar",
                    required = true,
                    example = "pikachu"
            )
            @PathVariable String name) {
        PokemonResponseDTO pokemon = pokemonService.getPokemonByName(name);
        return ResponseEntity.ok(pokemon);
    }
}
