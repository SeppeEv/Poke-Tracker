package com.example.application.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val height: Int,
    val weight: Int,
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