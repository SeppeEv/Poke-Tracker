package com.example.application.ui.screens.pokemon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application.network.PokeApiTest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/*sealed interface PokeUiState {
    data class Success(val pokemon: Pokemon) : PokeUiState
    object Error : PokeUiState
    object Loading : PokeUiState
}*/

sealed interface PokeUiStateTest {
    data class Success(val pokemon: String) : PokeUiStateTest
    object Error : PokeUiStateTest
    object Loading : PokeUiStateTest
}

class PokemonByTypeViewModel : ViewModel() {
    /*var pokeTrackerUiState: PokeUiState by mutableStateOf(PokeUiState.Loading)
        private set*/

    var pokeUiStateTest: PokeUiStateTest by mutableStateOf(PokeUiStateTest.Loading)
        private set
    init {
        //getPokemons()
        getPokemonTest()
    }

    fun getPokemonTest() {
        viewModelScope.launch {
            pokeUiStateTest = PokeUiStateTest.Loading
            pokeUiStateTest = try {
                val pokemon = PokeApiTest.retrofitService.getPokemon()
                PokeUiStateTest.Success(
                    pokemon = pokemon.name,
                )
            } catch (e: IOException) {
                PokeUiStateTest.Error
            } catch (e: HttpException) {
                PokeUiStateTest.Error
            } catch (e: Exception) {
                PokeUiStateTest.Error
            }
        }
    }

    /*fun getPokemons() {
        viewModelScope.launch {
            pokeTrackerUiState = PokeUiState.Loading
            pokeTrackerUiState = try {
                val pokemon = PokeTrackerApi.retrofitService.getPokemon()
                PokeUiState.Success(
                    pokemon = pokemon,
                )
            } catch (e: IOException) {
                PokeUiState.Error
            } catch (e: HttpException) {
                PokeUiState.Error
            }
        }
    }*/
}
