package com.example.application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritePokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(pokemon: FavoritePokemon)

    @Delete
    suspend fun removeFavorite(pokemon: FavoritePokemon)

    @Query("SELECT * FROM favorite_pokemon")
    suspend fun getFavorites(): List<FavoritePokemon>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_pokemon WHERE name = :name)")
    suspend fun isFavorite(name: String): Boolean

    @Query("SELECT * FROM favorite_pokemon WHERE name = :name LIMIT 1")
    suspend fun getFavoriteByName(name: String): FavoritePokemon?
}
