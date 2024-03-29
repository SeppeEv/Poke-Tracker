package com.example.application.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.model.GenerationsResponse
import com.example.application.model.PokemonTypesResponse
import com.example.application.network.PokeApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface HomeUiState {
    data class Success(val generations: GenerationsResponse, val types: PokemonTypesResponse) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

/**
 * ViewModel for the home-related screen. It handles interactions and data retrieval
 * for the home screen, including generations and types data.
 */
class HomeViewModel: ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                val generations = PokeApi.retrofitService.getGenerations()
                val types = PokeApi.retrofitService.getTypes()
                HomeUiState.Success(
                    generations = generations,
                    types = types
                )
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            } catch (e: Exception) {
                HomeUiState.Error
            }
        }
    }
}
