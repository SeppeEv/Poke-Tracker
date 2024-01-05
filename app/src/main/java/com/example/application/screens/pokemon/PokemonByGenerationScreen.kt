package com.example.application.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.R

@Composable
fun PokemonByGenerationScreen(
    pokemonByGenerationViewModel: PokemonByGenerationViewModel = viewModel(factory = PokemonByGenerationViewModel.Factory),
    generation: String? = "Generation 1",
    onSelectPokemon: (String) -> Unit,
) {
    val pokemonByGenerationState = pokemonByGenerationViewModel.uiState.collectAsState()

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
                    text = generation,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            PokemonListGrid(
                onSelectPokemon,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_medium)),
            )
        }
    }
}
