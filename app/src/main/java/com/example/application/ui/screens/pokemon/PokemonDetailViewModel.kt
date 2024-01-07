package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.PokemonResponse
import com.example.application.network.PokeApi
import kotlinx.coroutines.launch

sealed interface PokemonDetailUiState {
    data class Success(val pokemon: PokemonResponse) : PokemonDetailUiState
    object Error : PokemonDetailUiState
    object Loading : PokemonDetailUiState
}

class PokemonDetailViewModel : ViewModel() {
    var pokemonDetailUiState: PokemonDetailUiState by mutableStateOf(PokemonDetailUiState.Loading)
        private set

    init {
        getPokemonDetail()
    }

    private fun getPokemonDetail() {
        viewModelScope.launch {
            pokemonDetailUiState = PokemonDetailUiState.Loading
            pokemonDetailUiState = try {
                val pokemon = PokeApi.retrofitService.getPokemonByName("mew")
                PokemonDetailUiState.Success(
                    pokemon = pokemon
                )
            } catch (e: Exception) {
                TypeUiState.Error
            }
        }
    }
}
