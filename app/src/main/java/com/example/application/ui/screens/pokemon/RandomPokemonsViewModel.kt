package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.PokemonResponse
import com.example.application.network.PokeApi
import kotlinx.coroutines.launch

sealed interface RandomPokemonUiState {
    data class Success(val pokemon: PokemonResponse) : RandomPokemonUiState
    object Error : RandomPokemonUiState
    object Loading : RandomPokemonUiState
}

/**
 * ViewModel for the pokemon-related screen. It handles interactions and data retrieval
 * for the pokemon screen, including random pokemon data.
 */
class RandomPokemonsViewModel : ViewModel() {
    var randomPokemonUiState: RandomPokemonUiState by mutableStateOf(RandomPokemonUiState.Loading)
        private set

    init {
        getRandomPokemons()
    }

    private fun getRandomPokemons() {
        viewModelScope.launch {
            randomPokemonUiState = RandomPokemonUiState.Loading
            randomPokemonUiState = try {
                val randomId = (1..898).random()
                RandomPokemonUiState.Success(
                    pokemon = PokeApi.retrofitService.getPokemonById(randomId)
                )
            } catch (e: Exception) {
                RandomPokemonUiState.Error
            }
        }
    }

}
