package com.example.application.ui.screens.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.application.R

@Composable
fun LoadingScreen(modifier: Modifier) {
    Text(text = stringResource(R.string.loading), modifier = modifier)
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "",
        )*/
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun PokemonByTypeScreen(
    pokeTrackerUiState: PokeTrackerUiState,
    selectedType: String?,
    onSelectPokemon: (String) -> Unit,
) {
    when (pokeTrackerUiState) {
        is PokeTrackerUiState.Loading -> { LoadingScreen(modifier = Modifier.fillMaxSize()) }
        is PokeTrackerUiState.Success -> PokemonByTypeContent(
            pokeTrackerUiState.pokemons,
            selectedType,
            onSelectPokemon,
            modifier = Modifier.fillMaxSize(),
        )

        is PokeTrackerUiState.Error -> { ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun PokemonByTypeContent(
    pokemons: String,
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
            Text(text = "test")
            Text(text = pokemons, modifier = modifier)
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
