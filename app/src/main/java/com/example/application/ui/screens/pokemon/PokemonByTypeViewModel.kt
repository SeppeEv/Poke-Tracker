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

class PokemonByTypeViewModel : ViewModel() {
    var typeUiState: TypeUiState by mutableStateOf(TypeUiState.Loading)
        private set
    init {
        getPokemonByType()
    }

    fun getPokemonByType() {
        viewModelScope.launch {
            typeUiState = TypeUiState.Loading
            typeUiState = try {
                val type = PokeApi.retrofitService.getTypeByName("fire")
                TypeUiState.Success(
                    type = type
                )
            } catch (e: Exception) {
                TypeUiState.Error
            }
        }
    }
}
