package com.example.application.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val types: List<PokemonTypeEntry>,
    val sprites: PokemonSprites,
    val stats: List<PokemonStat>  // Include stats here
)

data class PokemonAbility(
    val ability: Ability
)

data class Ability(
    val name: String
)

data class PokemonTypeEntry(
    val type: Type
)

data class Type(
    val name: String
)

data class PokemonSprites(
    val frontDefault: String,
    val backDefault: String
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)
