package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.GenerationResponse
import com.example.application.network.PokeApi
import kotlinx.coroutines.launch

sealed interface GenerationUiState {
    data class Success(val generation: GenerationResponse) : GenerationUiState
    object Error : GenerationUiState
    object Loading : GenerationUiState
}

/**
 * ViewModel for the pokemon-related screen. It handles interactions and data retrieval
 * for the pokemon screen, including generation data.
 */
class PokemonByGenerationViewModel : ViewModel() {
    var generationUiState: GenerationUiState by mutableStateOf(GenerationUiState.Loading)
        private set

    private var currentGenerationId: Int? = null

    fun getPokemonByGeneration(generationId: Int) {
        if (currentGenerationId == generationId) {
            return
        }
        currentGenerationId = generationId

        viewModelScope.launch {
            generationUiState = GenerationUiState.Loading
            generationUiState = try {
                val generation = PokeApi.retrofitService.getGeneration(generationId)
                GenerationUiState.Success(
                    generation = generation
                )
            } catch (e: Exception) {
                GenerationUiState.Error
            }
        }

    }
}
