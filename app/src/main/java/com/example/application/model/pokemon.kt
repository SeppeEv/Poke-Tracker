package com.example.application.model

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>
)

data class PokemonAbility(
    val ability: Ability
)

data class Ability(
    val name: String,
)