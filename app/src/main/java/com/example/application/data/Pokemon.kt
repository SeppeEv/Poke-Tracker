package com.example.application.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents a Pokemon.
 *
 * @param id The id of the Pokemon.
 * @param name The name of the Pokemon.
 * @param height The height of the Pokemon.
 * @param weight The weight of the Pokemon.
 */
@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val height: Int,
    val weight: Int,
)