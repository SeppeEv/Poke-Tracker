package com.example.application.network

import com.example.application.model.PokemonTest
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
interface PokeApiTestService {
    @GET("1")
    suspend fun getPokemon(): PokemonTest
}

object PokeApiTest {
    val retrofitService: PokeApiTestService by lazy {
        retrofit.create(PokeApiTestService::class.java)
    }
}