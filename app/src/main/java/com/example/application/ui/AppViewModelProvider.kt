package com.example.application.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.application.ui.screens.pokemon.FavoriteEntryViewModel

/**
 * Factory for all ViewModels.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            FavoriteEntryViewModel(pokemonApplication().container.favoritesRepository)
        }
    }
}

fun CreationExtras.pokemonApplication(): MyApplication = (this[AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)