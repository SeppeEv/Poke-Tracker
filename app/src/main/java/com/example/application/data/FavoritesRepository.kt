package com.example.application.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface FavoritesRepository {
    /**
     * Retrieve all the favorites from the the given data source.
     */
    fun getAllFavoritesStream(): Flow<List<Favorite>>

    /**
     * Retrieve a favorite from the given data source that matches with the [id].
     */
    fun getFavoriteStream(id: Int): Flow<Favorite?>

    /**
     * Retrieve a favorite from the given data source that matches with the [name].
     */
    fun getFavoriteStreamByName(name: String): Flow<Favorite?>

    /**
     * Insert favorite in the data source
     */
    suspend fun insertFavorite(item: Favorite)

    /**
     * Delete favorite from the data source
     */
    suspend fun deleteFavorite(item: Favorite)

    /**
     * Update favorite in the data source
     */
    suspend fun updateFavorite(item: Favorite)
}