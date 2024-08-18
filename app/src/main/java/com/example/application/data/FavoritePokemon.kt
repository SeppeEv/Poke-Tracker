package com.example.application.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents a favorite Pokemon.
 *
 * @param id The id of the favorite Pokemon.
 * @param name The name of the favorite Pokemon.
 */
@Entity(tableName = "favorite_pokemon")
data class FavoritePokemon(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val name: String,
)
