package com.example.application.ui.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.example.application.R
import com.example.application.ui.screens.ErrorScreen
import com.example.application.ui.screens.LoadingScreen

@Composable
fun RandomPokemonsScreen(
    randomPokemonUiState: RandomPokemonUiState,
) {
    when (randomPokemonUiState) {
        is RandomPokemonUiState.Loading -> LoadingScreen(modifier = androidx.compose.ui.Modifier.fillMaxSize())
        is RandomPokemonUiState.Success -> {
            val pokemon = randomPokemonUiState.pokemon

            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = androidx.compose.ui.Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            ) {
                Column(
                    modifier = androidx.compose.ui.Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                ) {
                    if (pokemon != null) {
                        PokemonDetailCard(
                            pokemon = randomPokemonUiState.pokemon,
                            pokemonType = "TYPE",

                        )
                    }
                }
            }
        }
        is RandomPokemonUiState.Error -> ErrorScreen(modifier = androidx.compose.ui.Modifier.fillMaxSize())
    }
}