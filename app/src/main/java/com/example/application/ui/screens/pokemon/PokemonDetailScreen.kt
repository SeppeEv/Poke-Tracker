package com.example.application.ui.screens.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.application.R

@Composable
fun PokemonDetailScreen(
    selectedPokemon: String?,
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.Factory),
) {
    val pokemonDetailState by pokemonDetailViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        PokemonDetailCard(selectedPokemon, pokemonDetailState.pokemonType)
    }
}

@Composable
fun PokemonDetailCard(
    selectedPokemon: String?,
    pokemonType: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.pokemon_detail_card_corner_radius)),
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small)),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = selectedPokemon ?: "No pokemon found",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = "1",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.bulbasaur_sprite),
                contentDescription = stringResource(R.string.bulbasaur),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.pokemon_detail_image_corner_radius))),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = pokemonType,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = "Poison",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_ultra_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                StatusColumn("HP", "45")
                StatusColumn("Attack", "49")
                StatusColumn("Defense", "49")
                StatusColumn("Sp. Atk", "65")
                StatusColumn("Sp. Def", "65")
                StatusColumn("Speed", "45")
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
            Text(
                text = stringResource(R.string.height) + ": " + "2'04\"",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.weight) + ": " + "15.2 lbs",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.abilities) + ": ",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = "Overgrow, Chlorophyll",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
private fun StatusColumn(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun PokemonScreenPreview() {
    Surface {
        PokemonDetailScreen(
            selectedPokemon = "Bulbasaur",
        )
    }
}
