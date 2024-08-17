package com.example.application.ui.screens.pokemon

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.application.R
import com.example.application.ui.screens.ErrorScreen
import com.example.application.ui.screens.LoadingScreen

/**
 * Screen that displays a list of pokemons by generation.
 *
 * @param generationUiState The state of the generation.
 * @param onSelectPokemon Called when a pokemon is selected.
 */
@Composable
fun PokemonByGenerationScreen(
    generationUiState: GenerationUiState,
    onSelectPokemon: (String) -> Unit,
) {
    when (generationUiState) {
        is GenerationUiState.Loading -> {
            Log.d("PokemonByGenerationScreen", "Displaying loading screen")
            LoadingScreen(
                modifier = Modifier.fillMaxSize(),
            )}
        is GenerationUiState.Success -> {
            Log.d("PokemonByGenerationScreen", "Displaying succes screen")
            val generation = generationUiState.generation

            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                ) {
                    if (generation != null) {
                        Text(
                            text = "Generation ${generation.id}",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        PokemonByGenerationListGrid(
                            pokemons = generation.pokemon_species,
                            onSelectPokemon,
                            modifier = Modifier
                                .padding(top = dimensionResource(id = R.dimen.padding_medium)),
                        )
                    }
                }
            }
        }
        is GenerationUiState.Error -> {
            Log.e("PokemonByGenerationScreen", "Error screen")
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}