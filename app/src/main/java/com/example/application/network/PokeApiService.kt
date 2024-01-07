package com.example.application.network

import com.example.application.model.GenerationResponse
import com.example.application.model.GenerationsResponse
import com.example.application.model.PokemonResponse
import com.example.application.model.PokemonTypeResponse
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface PokeApiService {
    @GET("pokemon/{id}/")
    suspend fun getPokemon(@Path("id") pokemonId: Int): PokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getPokemonByName(@Path("name") pokemonName: String): PokemonResponse

    @GET("type")
    suspend fun getTypes(): PokemonTypeResponse

    @GET("generation")
    suspend fun getGenerations(): GenerationsResponse

    @GET("generation/{id}/")
    suspend fun getGeneration(@Path("id") generationId: Int): GenerationResponse
}

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}