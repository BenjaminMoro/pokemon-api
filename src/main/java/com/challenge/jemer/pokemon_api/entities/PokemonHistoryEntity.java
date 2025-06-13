package com.challenge.jemer.pokemon_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pokemon_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageUrl;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PokemonType> types;
    private LocalDateTime searchDate;
}
