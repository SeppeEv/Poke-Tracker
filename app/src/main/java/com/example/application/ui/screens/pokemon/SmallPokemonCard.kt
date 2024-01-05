package com.example.application.ui.screens.pokemon

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.application.R
import com.example.application.ui.theme.ApplicationTheme

@Composable
fun PokemonCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
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
                    onSelectPokemon("Charizard")
                },
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.pokemon_card_image_size)),
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.padding_small)),
            )
        }
    }
}

@Composable
fun PokemonListGrid(
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
        items(151) {
            PokemonCard(
                drawable = R.drawable.bulbasaur_sprite,
                text = R.string.bulbasaur,
                onSelectPokemon,
                Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .testTag("PokemonCard"),
            )
        }
    }
}

@Preview
@Composable
fun PokemonListGridPreview() {
    ApplicationTheme {
        PokemonListGrid(
            onSelectPokemon = {},
        )
    }
}
