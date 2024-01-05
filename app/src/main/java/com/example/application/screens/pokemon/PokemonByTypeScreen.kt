package com.example.application.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokemonByTypeScreen(
    pokemomByTypeViewModel: PokemonByTypeViewModel = viewModel(factory = PokemonByTypeViewModel.Factory),
    selectedType: String?,
    onSelectPokemon: (String) -> Unit,
) {
    val pokemonByTypeState = pokemomByTypeViewModel.uiState.collectAsState()

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            if (selectedType != null) {
                Text(
                    text = selectedType,
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
            PokemonListGrid(
                onSelectPokemon,
                modifier = Modifier
                    .padding(top = 16.dp),
            )
        }
    }
}
