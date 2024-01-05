package com.example.application.ui.screens.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonByTypeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PokemonByTypeState())
    val uiState: StateFlow<PokemonByTypeState> = _uiState.asStateFlow()

    private var pokemons: List<String> = listOf()

    fun getPokemons(): List<String> {
        return pokemons
    }

    init {
        _uiState.value = PokemonByTypeState(
            pokemons = getPokemons(),
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PokemonByTypeViewModel()
            }
        }
    }
}
