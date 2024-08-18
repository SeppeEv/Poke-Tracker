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

/**
 * ViewModel for the pokemon-related screen. It handles interactions and data retrieval
 * for the pokemon screen, including pokemon data.
 */
class PokemonDetailViewModel : ViewModel() {
    var pokemonDetailUiState: PokemonDetailUiState by mutableStateOf(PokemonDetailUiState.Loading)
        private set
    private var currentPokemonName: String? = null
    fun getPokemonDetail(pokemonName: String) {
        if (currentPokemonName == pokemonName) {
            return
        }
        currentPokemonName = pokemonName

        viewModelScope.launch {
            pokemonDetailUiState = PokemonDetailUiState.Loading
            pokemonDetailUiState = try {
                val pokemon = PokeApi.retrofitService.getPokemonByName(pokemonName)
                PokemonDetailUiState.Success(
                    pokemon = pokemon
                )
            } catch (e: Exception) {
                TypeUiState.Error
            }
        }
    }
}
