package com.example.application.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("results")
    val results: List<PokemonListItem>
)

@Serializable
data class PokemonListItem(
    @SerialName("name")
    val name: String,

    @SerialName("url")
    val url: String
)
