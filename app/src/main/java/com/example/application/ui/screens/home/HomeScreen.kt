package com.example.application.ui.screens.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.model.GenerationsResults
import com.example.application.model.TypeResults
import com.example.application.ui.screens.ErrorScreen
import com.example.application.ui.screens.LoadingScreen

/**
 * Home screen for the app.
 *
 * @param homeUiState The state of the home screen.
 * @param onTypeClicked Called when a type is clicked.
 * @param onGenerationClicked Called when a generation is clicked.
 * @param modifier Modifier for styling.
 */
@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onTypeClicked: (String) -> Unit = {},
    onGenerationClicked: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    when (homeUiState) {
        is HomeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is HomeUiState.Success -> {
            val types = homeUiState.types.results
            val generations = homeUiState.generations.results
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
                    HomeSection(title = R.string.types) {
                        TypeElementRow(onTypeClicked, types)
                    }
                    HomeSection(title = R.string.generations) {
                        GenerationCardGrid(onGenerationClicked, generations)
                    }
                }
            }
        }
        is HomeUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}

/**
 * Element for each type.
 *
 * @param drawable The image for the type.
 * @param text The name of the type.
 * @param onTypeClicked Called when the type is clicked.
 * @param modifier Modifier for styling.
 */
@Composable
fun TypeElement(
    @DrawableRes drawable: Int,
    text: String,
    onTypeClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .testTag("TypeElement")
            .clickable {
                // TODO: Pass the correct type to the OnTypeClicked lambda
                onTypeClicked(text)
            },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Image(
                painter = painterResource(typeImage(type = text)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape),
            )
            Text(
                text = text,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

/**
 * Function to get the correct image for each generation.
 *
 * @param gen The name of the generation.
 */
@Composable
fun generationImage(
    gen: String
): Int {
    if (gen == "generation-i") {
        return R.drawable.pokemon_red__1_
    }
    else if (gen == "generation-ii") {
        return R.drawable.pokemon_silver__2_
    }
    else if (gen == "generation-iii") {
        return R.drawable.pokemon_emerald__3_
    }
    else if (gen == "generation-iv") {
        return R.drawable.pokemon_diamond__4_
    }
    else if (gen == "generation-v") {
        return R.drawable.pokemon_black__5_
    }
    else if (gen == "generation-vi") {
        return R.drawable.pokemon_x__6_
    }
    else if (gen == "generation-vii") {
        return R.drawable.pokemon_sun_7
    }
    else if (gen == "generation-viii") {
        return R.drawable.pokemon_sword_8
    }
    else if (gen == "generation-ix") {
        return R.drawable.pokemon_scarletviolet_9
    }
    else {
        return R.drawable.pokebal_sprite
    }
}

/**
 * Function to get the correct image for each type.
 *
 * @param type The name of the type.
 */
@Composable
fun typeImage(
    type: String
): Int {
    if (type == "normal") {
        return R.drawable.normal_sprite
    }
    else if (type == "fighting") {
        return R.drawable.fighting_sprite
    }
    else if (type == "flying") {
        return R.drawable.flying_sprite
    }
    else if (type == "poison") {
        return R.drawable.poison_type
    }
    else if (type == "ground") {
        return R.drawable.ground_sprite
    }
    else if (type == "rock") {
        return R.drawable.rock_sprite
    }
    else if (type == "bug") {
        return R.drawable.bug_sprite
    }
    else if (type == "ghost") {
        return R.drawable.ghost_sprite
    }
    else if (type == "steel") {
        return R.drawable.steel_sprite
    }
    else if (type == "fire") {
        return R.drawable.fire_symbol_sprite
    }
    else if (type == "water") {
        return R.drawable.water_sprite
    }
    else if (type == "grass") {
        return R.drawable.grass_sprite
    }
    else if (type == "electric") {
        return R.drawable.electric_sprite
    }
    else if (type == "psychic") {
        return R.drawable.psychic_sprite
    }
    else if (type == "ice") {
        return R.drawable.ice_sprite
    }
    else if (type == "dragon") {
        return R.drawable.dragon_sprite
    }
    else if (type == "dark") {
        return R.drawable.dark_sprite
    }
    else if (type == "fairy") {
        return R.drawable.fairy_sprite
    }
    else {
        return R.drawable.pokebal_sprite
    }
}

/**
 * Represents a generation card.
 *
 * @param gen The name of the generation.
 * @param onGenerationClicked Called when the generation is clicked.
 * @param modifier Modifier for styling.
 */
@Composable
fun GenerationCard(
    gen: String,
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
                    Log.d("GenerationCard", "Generation clicked: ${genNameToInt(gen).toString()}")
                    onGenerationClicked(genNameToInt(gen).toString())
                },

        ) {
            Image(
                painter = painterResource(generationImage(gen)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp),
            )
            Text(
                text = gen,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
            )
        }
    }
}

/**
 * Represents a row of type cards.
 *
 * @param onTypeClicked Called when a type is clicked.
 * @param types The list of types.
 * @param modifier Modifier for styling.
 */
@Composable
fun TypeElementRow(
    onTypeClicked: (String) -> Unit,
    types: List<TypeResults>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small)),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        types.forEach { type ->
            item {
                TypeElement(
                    drawable = R.drawable.grass_sprite,
                    text = type.name,
                    onTypeClicked = onTypeClicked,
                )
            }
        }
    }
}

/**
 * Represents a grid of generation cards.
 *
 * @param onGenerationClicked Called when a generation is clicked.
 * @param generations The list of generations.
 * @param modifier Modifier for styling.
 */
@Composable
fun GenerationCardGrid(
    onGenerationClicked: (String) -> Unit,
    generations: List<GenerationsResults>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium)),
        modifier = modifier
            .height(dimensionResource(id = R.dimen.generations_grid_height)),
    ) {
        items(generations) { gen ->
            GenerationCard(
                gen = gen.name,
                onGenerationClicked = onGenerationClicked,
                Modifier.height(dimensionResource(id = R.dimen.generations_card_height)),
            )
        }
    }
}

/**
 * Represents a section of the home screen.
 *
 * @param title The title of the section.
 * @param modifier Modifier for styling.
 * @param content The content of the section.
 */
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

fun genNameToInt(name: String) : Int {
    if (name == "generation-i") {
        return 1
    }
    else if (name == "generation-ii") {
        return 2
    }
    else if (name == "generation-iii") {
        return 3
    }
    else if (name == "generation-iv") {
        return 4
    }
    else if (name == "generation-v") {
        return 5
    }
    else if (name == "generation-vi") {
        return 6
    }
    else if (name == "generation-vii") {
        return 7
    }
    else if (name == "generation-viii") {
        return 8
    }
    else if (name == "generation-ix") {
        return 9
    }
    else {
        return 0
    }
}
