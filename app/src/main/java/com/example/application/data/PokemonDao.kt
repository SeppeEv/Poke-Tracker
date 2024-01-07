package com.example.application.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Update
    suspend fun update(pokemon: Pokemon)

    @Delete
    suspend fun delete(pokemon: Pokemon)

    @Query("SELECT * from pokemon WHERE id = 1")
    fun getPokemon(): Pokemon

    @Query("SELECT * from pokemon ORDER BY name ASC")
    fun getAllPokemon(): List<Pokemon>
}