package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.application.network.PokeTrackerApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface PokeTrackerUiState {
    data class Success(val pokemons: String) : PokeTrackerUiState
    object Error : PokeTrackerUiState
    object Loading : PokeTrackerUiState
}

class PokemonByTypeViewModel : ViewModel() {
    var pokeTrackerUiState: PokeTrackerUiState by mutableStateOf(PokeTrackerUiState.Loading)
        private set
    init {
        getPokemons()
    }

    fun getPokemons() {
        viewModelScope.launch {
            pokeTrackerUiState = PokeTrackerUiState.Loading
            pokeTrackerUiState = try {
                val listResult = PokeTrackerApi.retrofitService.getPokemons()
                PokeTrackerUiState.Success(
                    "Success: ${listResult.results.size} Pokemons retrieved",
                )
            } catch (e: IOException) {
                PokeTrackerUiState.Error
            } catch (e: HttpException) {
                PokeTrackerUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PokemonByTypeViewModel()
            }
        }
    }
}
