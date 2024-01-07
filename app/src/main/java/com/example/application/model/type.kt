package com.example.application.model

data class PokemonTypeResponse(
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