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
 * Screen that displays a list of pokemons by type.
 *
 * @param typeUiState The state of the type.
 * @param onSelectPokemon Called when a pokemon is selected.
 */
@Composable
fun PokemonByTypeScreen(
    typeUiState: TypeUiState,
    onSelectPokemon: (String) -> Unit,
) {
    when (typeUiState) {
        is TypeUiState.Loading -> {
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        is TypeUiState.Success -> {
            val type = typeUiState.type

            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                ) {
                    if (type != null) {
                        Text(
                        text = type.name,
                        style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                    PokemonByTypeListGrid(
                        pokemons = type.pokemon,
                        onSelectPokemon = onSelectPokemon)
                }
            }
        }

        is TypeUiState.Error ->  {
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}