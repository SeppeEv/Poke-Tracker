package com.example.application.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonByGenerationScreen(
    generation: String? = "Generation 1",
    onSelectPokemon: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
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
                    .padding(top = 16.dp),
            )
        }
    }
}

