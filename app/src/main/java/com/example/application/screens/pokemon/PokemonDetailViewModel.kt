package com.example.application.screens.pokemon

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailState())
    val uiState: StateFlow<PokemonDetailState> = _uiState.asStateFlow()

    private var pokemonName: String = ""
    private var pokemonType: String = ""

    fun getPokemonName(): String {
        return pokemonName
    }

    fun getPokemonType(): String {
        return pokemonType
    }

    init {
        _uiState.value = PokemonDetailState(
            pokemonName = _uiState.value.pokemonName,
            pokemonType = _uiState.value.pokemonType,
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PokemonDetailViewModel()
            }
        }
    }
}
