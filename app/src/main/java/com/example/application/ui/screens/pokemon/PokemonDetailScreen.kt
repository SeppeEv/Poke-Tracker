package com.example.application.ui.screens.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.application.R
import com.example.application.model.PokemonResponse
import com.example.application.ui.screens.ErrorScreen
import com.example.application.ui.screens.LoadingScreen

/**
 * Screen that displays the details of a pokemon.
 *
 * @param pokemonDetailUiState The state of the pokemon.
 */
@Composable
fun PokemonDetailScreen(
    pokemonDetailUiState: PokemonDetailUiState,
) {
    when (pokemonDetailUiState) {
        is PokemonDetailUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is PokemonDetailUiState.Success -> {
            val pokemon = pokemonDetailUiState.pokemon

            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                ) {
                    if (pokemon != null) {
                        PokemonDetailCard(
                            pokemon = pokemonDetailUiState.pokemon,
                            pokemonType = "TYPE",
                        )
                    }
                }
            }
        }
        is PokemonDetailUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
        else -> {}
    }
}

/**
 * Card that displays the details of a pokemon.
 *
 * @param pokemon The pokemon to display.
 * @param pokemonType The type of the pokemon.
 */
@Composable
fun PokemonDetailCard(
    pokemon: PokemonResponse,
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
                    text = pokemon.name ?: "No pokemon found",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                )
                Text(
                    text = pokemon.id.toString(),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                )
            }
            Image(
                painter = rememberAsyncImagePainter(model = pokemon.sprites.frontDefault),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.pokemon_detail_image_corner_radius))),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                pokemon.types.forEach { type ->
                    Text(
                        text = type.type.name,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_ultra_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                StatusColumn(stringResource(R.string.hp), pokemon.stats[0].baseStat.toString())
                StatusColumn(stringResource(R.string.attack), pokemon.stats[1].baseStat.toString())
                StatusColumn(stringResource(R.string.defense), pokemon.stats[2].baseStat.toString())
                StatusColumn(stringResource(R.string.sp_atk), pokemon.stats[3].baseStat.toString())
                StatusColumn(stringResource(R.string.sp_def), pokemon.stats[4].baseStat.toString())
                StatusColumn(stringResource(R.string.speed), pokemon.stats[5].baseStat.toString())
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
            Text(
                text = stringResource(R.string.height) + ": " + pokemon.height.toString() + " ft",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = stringResource(R.string.weight) + ": " + pokemon.weight.toString() + " lbs",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                pokemon.abilities.forEach { ability ->
                    Text(
                        text = ability.ability.name,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_small)),
                    )
                }
            }
        }
    }
}

/**
 * Column that displays the status of a pokemon.
 *
 * @param title The title of the status.
 * @param value The value of the status.
 */
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