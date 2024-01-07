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
    val stats: List<PokemonStat>
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
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("back_default")
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

data class PokemonSpecies(
    val name: String,
    val url: String
)