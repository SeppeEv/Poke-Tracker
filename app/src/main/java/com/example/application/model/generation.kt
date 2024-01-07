package com.example.application.model

data class GenerationsResponse(
    val results: List<GenerationsResults>
)

data class GenerationsResults(
    val name: String,
    val url: String
)

data class GenerationResponse(
    val id: Int,
    val pokemon_species: List<PokemonSpecies>
)

data class PokemonSpecies(
    val name: String,
    val url: String
)

