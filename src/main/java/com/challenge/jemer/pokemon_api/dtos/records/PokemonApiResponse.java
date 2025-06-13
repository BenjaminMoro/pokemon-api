package com.challenge.jemer.pokemon_api.dtos.records;


import java.util.List;

public record PokemonApiResponse(String name, Sprite sprites, List<TypeWrapper> types) {
}