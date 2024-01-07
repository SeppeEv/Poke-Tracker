package com.example.application.ui.screens.pokemon

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.network.PokeApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface PokeUiState {
    data class Success(val pokemon: String) : PokeUiState
    object Error : PokeUiState
    object Loading : PokeUiState
}

class PokemonByTypeViewModel : ViewModel() {
    /*var pokeTrackerUiState: PokeUiState by mutableStateOf(PokeUiState.Loading)
        private set*/

    var pokeUiStateTest: PokeUiState by mutableStateOf(PokeUiState.Loading)
        private set
    init {
        //getPokemons()
        getPokemonTest()
    }

    fun getPokemonTest() {
        viewModelScope.launch {
            pokeUiStateTest = PokeUiState.Loading
            pokeUiStateTest = try {
                val pokemon = PokeApi.retrofitService.getPokemon(1)
                PokeUiState.Success(
                    pokemon = pokemon.toString(),
                )
            } catch (e: IOException) {
                PokeUiState.Error
            } catch (e: HttpException) {
                PokeUiState.Error
            } catch (e: Exception) {
                Log.e("PokemonByTypeViewModel", "getPokemonTest: ${e.message}")
                PokeUiState.Error
            }
        }
    }
}
