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
    val name: String,
    val pokemon_species: List<PokemonSpecies>
)


