package com.example.application.network

import com.example.application.model.PokemonListResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface PokeTrackerApiService {
    @GET("pokemon")
    suspend fun getPokemons(): PokemonListResponse
}

object PokeTrackerApi {
    val retrofitService: PokeTrackerApiService by lazy {
        retrofit.create(PokeTrackerApiService::class.java)
    }
}
