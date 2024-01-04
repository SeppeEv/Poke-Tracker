package com.example.application.screens.pokemon

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(60.dp)
                .clickable {
                    onSelectPokemon("Charizard")
                },
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp),
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(8.dp),
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
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .width(300.dp),
    ) {
        items(151) {
            PokemonCard(
                drawable = R.drawable.bulbasaur_sprite,
                text = R.string.bulbasaur,
                onSelectPokemon,
                Modifier.padding(8.dp),
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
