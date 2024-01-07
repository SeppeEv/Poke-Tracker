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

@Composable
fun PokemonByTypeScreen(
    pokeUiState: PokeUiState,
    selectedType: String?,
    onSelectPokemon: (String) -> Unit,
) {
    when (pokeUiState) {
        is PokeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is PokeUiState.Success -> {
            Log.d("PokemonByTypeScreen", "pokemonUiStateTest: ${pokeUiState.pokemon}")
            PokemonByTypeContent(
                pokeUiState.pokemon,
                selectedType,
                onSelectPokemon,
                modifier = Modifier.fillMaxSize(),
            )
        }

        is PokeUiState.Error ->  {
            Log.d("PokemonByTypeScreen", "pokemonUiStateTest: ${pokeUiState}")
            ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun PokemonByTypeContent(
    pokemon: String,
    selectedType: String?,
    onSelectPokemon: (String) -> Unit,
    modifier: Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxSize(),
        ) {
            Text(text = pokemon, modifier = modifier)
            if (selectedType != null) {
                Text(
                    text = selectedType,
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
