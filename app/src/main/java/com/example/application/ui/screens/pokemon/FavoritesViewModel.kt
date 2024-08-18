package com.example.application.ui.screens.pokemon

import PokemonsRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(private val pokemonsRepository: PokemonsRepository) : ViewModel() {
    val favoritePokemons = flow {
        emit(pokemonsRepository.getFavorites())
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
