package com.example.application.screens.pokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoritePokemonsScreen(
    favoritePokemonsViewModel: FavoritePokemonsViewModel = viewModel(factory = FavoritePokemonsViewModel.Factory),
) {
    val favoritePokemonsState = favoritePokemonsViewModel.uiState.collectAsState()

    Column {
        Text(text = "Favorite Pokemons Screen")
        for (pokemon in favoritePokemonsState.value.pokemons) {
            Text(text = pokemon)
        }
    }
}

@Preview
@Composable
fun FavoritePokemonsScreenPreview() {
    FavoritePokemonsScreen()
}
