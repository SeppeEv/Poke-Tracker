package com.example.application.data

import android.content.Context

interface AppContainer {
    val pokemonsRepository: PokemonsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val pokemonsRepository: PokemonsRepository by lazy {
        OfflinePokemonsRepository(PokemonDatabase.getDatabase(context).pokemonDao())
    }
}