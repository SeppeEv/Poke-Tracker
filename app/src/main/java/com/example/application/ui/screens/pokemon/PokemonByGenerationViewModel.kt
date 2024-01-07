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
class PokemonByGenerationViewModel : ViewModel() {
    var generationUiState: GenerationUiState by mutableStateOf(GenerationUiState.Loading)
        private set

    init {
        getPokemonByGeneration()
    }

    private fun getPokemonByGeneration() {
       viewModelScope.launch {
           generationUiState = GenerationUiState.Loading
           generationUiState = try {
               val generation = PokeApi.retrofitService.getGeneration(1)
               GenerationUiState.Success(
                   generation = generation
               )
           } catch (e: Exception) {
               GenerationUiState.Error
           }
       }
    }
}
