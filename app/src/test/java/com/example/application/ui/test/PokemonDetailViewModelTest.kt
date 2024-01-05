package com.example.application.ui.test

import com.example.application.screens.pokemon.PokemonDetailViewModel
import org.junit.Test

class PokemonDetailViewModelTest {
    private val viewModel = PokemonDetailViewModel()

    @Test
    fun test_Correct_PokemonDetailViewModel() {
        var currentPokemonDetailState = viewModel.uiState.value

        assert(currentPokemonDetailState.pokemonName == "Mew")
        assert(currentPokemonDetailState.pokemonType == "Psychic")
    }

    @Test
    fun test_Incorrect_PokemonDetailViewModel() {
        var currentPokemonDetailState = viewModel.uiState.value

        assert(currentPokemonDetailState.pokemonName != "Pikachu")
        assert(currentPokemonDetailState.pokemonType != "Electric")
    }
}
