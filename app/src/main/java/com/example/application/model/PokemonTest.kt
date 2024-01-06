package com.example.application.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonTest(
    //val id: Int,
    val name: String,
    //val height: Int,
    //val abilities: List<AbilityContainerTest>
)

@Serializable
data class AbilityContainerTest(
    val ability: AbilityTest
)

@Serializable
data class AbilityTest(
    val name: String,
)
