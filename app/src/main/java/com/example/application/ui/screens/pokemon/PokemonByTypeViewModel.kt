package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.TypeResponse
import com.example.application.network.PokeApi
import kotlinx.coroutines.launch


sealed interface TypeUiState {
    data class Success(val type: TypeResponse) : TypeUiState
    object Error : TypeUiState, PokemonDetailUiState
    object Loading : TypeUiState
}

/**
 * ViewModel for the pokemon-related screen. It handles interactions and data retrieval
 * for the pokemon screen, including type data.
 */
class PokemonByTypeViewModel : ViewModel() {
    var typeUiState: TypeUiState by mutableStateOf(TypeUiState.Loading)
        private set

    private var currentType: String? = null

    fun getPokemonByType(type: String) {
        if (currentType == type) {
            return
        }
        currentType = type
        viewModelScope.launch {
            typeUiState = TypeUiState.Loading
            typeUiState = try {
                val type = PokeApi.retrofitService.getTypeByName(type)
                TypeUiState.Success(
                    type = type
                )
            } catch (e: Exception) {
                TypeUiState.Error
            }
        }
    }
}
