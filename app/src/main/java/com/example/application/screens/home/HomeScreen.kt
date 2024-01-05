package com.example.application.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.ui.theme.ApplicationTheme

@Composable
fun HomeScreen(
    onTypeClicked: (String) -> Unit = {},
    onGenerationClicked: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val image = painterResource(R.drawable.lugia_background)

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(),
            alpha = 0.5f,
        )
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))
            Searchbar(Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium)))
            HomeSection(title = R.string.types) {
                TypeElementRow(onTypeClicked)
            }
            HomeSection(title = R.string.generations) {
                GenerationCardGrid(onGenerationClicked)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun HomeScreenContentPreview() {
    ApplicationTheme {
        HomeScreen(onGenerationClicked = {})
    }
}

@Composable
fun Searchbar(
    modifier: Modifier = Modifier,
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
    )
}

@Composable
fun TypeElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    onTypeClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .testTag("TypeElement")
            .clickable {
                // TODO: Pass the correct type to the OnTypeClicked lambda
                onTypeClicked("GRASS")
            },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clip(CircleShape),
            )
            Text(
                text = stringResource(text),
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun TypeElementPreview() {
    ApplicationTheme {
        TypeElement(
            drawable = R.drawable.grass_sprite,
            text = R.string.grass_type,
            onTypeClicked = {},
            Modifier
                .padding(dimensionResource(id = R.dimen.padding_small)),
        )
    }
}

@Composable
fun GenerationCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    onGenerationClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .testTag("GenerationCard"),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(255.dp)
                .clickable {
                    // TODO: Pass the correct generation to the OnGenerationClicked lambda
                    onGenerationClicked("generation_10")
                },

        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp),
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            )
        }
    }
}

@Preview
@Composable
fun GenerationCardPreview() {
    ApplicationTheme {
        GenerationCard(
            drawable = R.drawable.pokemon_red__1_,
            text = R.string.generation_1,
            onGenerationClicked = {},
            Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
        )
    }
}

@Composable
fun TypeElementRow(
    onTypeClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        val typesData = listOf(
            "grass",
            "fire",
            "water",
            "bug",
            "normal",
            "poison",
            "electric",
            "ground",
        )
        items(typesData) { item ->
            TypeElement(
                drawable = R.drawable.grass_sprite,
                text = R.string.grass_type,
                onTypeClicked = onTypeClicked,
            )
        }
    }
}

@Composable
fun GenerationCardGrid(
    onGenerationClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium)),
        modifier = modifier
            .height(dimensionResource(id = R.dimen.generations_grid_height)),
    ) {
        val generationsData = listOf(
            "generation_1",
            "generation_2",
            "generation_3",
            "generation_4",
            "generation_5",
            "generation_6",
            "generation_7",
            "generation_8",
        )
        items(generationsData) { item ->
            GenerationCard(
                drawable = R.drawable.pokemon_red__1_,
                text = R.string.generation_1,
                onGenerationClicked = onGenerationClicked,
                Modifier.height(dimensionResource(id = R.dimen.generations_card_height)),
            )
        }
    }
}

@Preview
@Composable
fun GenerationCardGridPreview() {
    ApplicationTheme {
        GenerationCardGrid(
            onGenerationClicked = {},
        )
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Column(modifier) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(
                        top = dimensionResource(id = R.dimen.padding_top_homesection),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
            )
            content()
        }
    }
}
