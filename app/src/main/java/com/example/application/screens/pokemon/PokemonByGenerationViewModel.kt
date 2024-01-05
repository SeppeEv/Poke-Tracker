package com.example.application.screens.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonByGenerationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonByGenerationState())
    val uiState: StateFlow<PokemonByGenerationState> = _uiState.asStateFlow()

    private var pokemons: List<String> = listOf()

    fun getPokemons(): List<String> {
        return pokemons
    }

    init {
        pokemons = getPokemons()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PokemonByGenerationViewModel()
            }
        }
    }
}
