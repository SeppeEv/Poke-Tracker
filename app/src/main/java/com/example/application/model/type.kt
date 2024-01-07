package com.example.application.model

/**
 * Data classes for the Pokemon API responses.
 */
data class PokemonTypesResponse(
    val results: List<TypeResults>
)

data class TypeResults(
    val name: String,
    val url: String
)

data class PokemonType(
    val id: Int,
    val name: String,
)

data class TypeResponse(
    val id: Int,
    val name: String,
    val pokemon: List<PokemonsOfType>
)

data class PokemonsOfType(
    val pokemon: PokemonSpecies
)