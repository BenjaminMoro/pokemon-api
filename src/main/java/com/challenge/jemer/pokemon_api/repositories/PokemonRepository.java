package com.challenge.jemer.pokemon_api.repositories;

import com.challenge.jemer.pokemon_api.entities.PokemonHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonHistoryEntity, Long> {
}
