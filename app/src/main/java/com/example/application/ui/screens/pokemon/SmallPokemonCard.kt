package com.example.application.ui.screens.pokemon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import com.example.application.R
import com.example.application.model.PokemonSpecies
import com.example.application.model.PokemonsOfType

@Composable
fun PokemonCard(
    pokemon: PokemonSpecies,
    onSelectPokemon: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.pokemon_card_width))
                .clickable {
                    onSelectPokemon(pokemon.name)
                },
        ) {
            Text(
                text = pokemon.name ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small)),
            )
        }
    }
}

@Composable
fun PokemonByGenerationListGrid(
    pokemons: List<PokemonSpecies>,
    onSelectPokemon: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(pokemons.size) { index ->
            PokemonCard(
                pokemon = pokemons[index],
                onSelectPokemon = onSelectPokemon,
                modifier = Modifier
                    .testTag("pokemon_card_$index"),
            )
        }
    }
}

@Composable
fun PokemonByTypeListGrid(
    pokemons: List<PokemonsOfType>,
    onSelectPokemon: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(vertical = dimensionResource(id = R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(pokemons.size) { index ->
            PokemonCard(
                pokemon = pokemons[index].pokemon,
                onSelectPokemon = onSelectPokemon,
                modifier = Modifier
                    .testTag("pokemon_card_$index"),
            )
        }
    }
}