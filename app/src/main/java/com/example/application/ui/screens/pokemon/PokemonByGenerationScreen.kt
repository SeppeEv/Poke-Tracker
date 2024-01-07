package com.example.application.ui.screens.pokemon

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
        is GenerationUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is GenerationUiState.Success -> {
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
                            text = generation.id.toString(),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                    PokemonByGenerationListGrid(
                        pokemons = generation.pokemon_species,
                        onSelectPokemon,
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.padding_medium)),
                    )
                }
            }
        }
        is GenerationUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}