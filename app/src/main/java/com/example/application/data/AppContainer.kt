package com.example.application.data

import android.content.Context

/**
 * An interface defining how to get the required data.
 */
interface AppContainer {
    val favoritesRepository: FavoritesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val favoritesRepository: FavoritesRepository by lazy {
        OfflineFavoritesRepository(FavoriteDatabase.getDatabase(context).favoritePokemonDao())
    }
}