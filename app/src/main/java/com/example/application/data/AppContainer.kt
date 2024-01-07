package com.example.application.data

import android.content.Context

/**
 * An interface defining how to get the required data.
 */
interface AppContainer {
    val pokemonsRepository: PokemonsRepository
}

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
class AppDataContainer(private val context: Context) : AppContainer {
    override val pokemonsRepository: PokemonsRepository by lazy {
        OfflinePokemonsRepository(PokemonDatabase.getDatabase(context).pokemonDao())
    }
}