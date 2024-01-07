package com.example.application.data

import kotlinx.coroutines.flow.Flow

class OfflinePokemonsRepository(private val pokemonDao: PokemonDao) : PokemonsRepository {
    override suspend fun getPokemon(id: Int): Flow<Pokemon> = pokemonDao.getPokemon(id)
    override suspend fun getAllPokemon(): Flow<List<Pokemon>> = pokemonDao.getAllPokemon()

    override suspend fun insert(pokemon: Pokemon) = pokemonDao.insert(pokemon)

    override suspend fun update(pokemon: Pokemon) = pokemonDao.update(pokemon)

    override suspend fun delete(pokemon: Pokemon) = pokemonDao.delete(pokemon)
}