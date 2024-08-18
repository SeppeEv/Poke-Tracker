package com.example.application.ui.screens.pokemon


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = viewModel() ){
    val favoritePokemons by viewModel.favoritePokemons.collectAsState()

    LazyColumn {
        items(favoritePokemons) { pokemon ->
            Text(text = pokemon.name)
            // You can add more details and styling as needed
        }
    }
}