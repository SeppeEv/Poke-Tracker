package com.example.application.data

import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {

    /*
     * Returns a Pokemon with the given id.
     */
    suspend fun getPokemon(id: Int): Flow<Pokemon>

    /*
     * Returns a list of all Pokemon.
     */
    suspend fun getAllPokemon(): Flow<List<Pokemon>>

    /*
     * Inserts a Pokemon into the database.
     */
    suspend fun insert(pokemon: Pokemon)

    /*
     * Updates a Pokemon in the database.
     */
    suspend fun update(pokemon: Pokemon)

    /*
     * Deletes a Pokemon from the database.
     */
    suspend fun delete(pokemon: Pokemon)
}