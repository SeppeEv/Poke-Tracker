import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.application.R
import com.example.application.model.PokemonResponse
import com.example.application.ui.AppViewModelProvider
import com.example.application.ui.screens.ErrorScreen
import com.example.application.ui.screens.LoadingScreen
import com.example.application.ui.screens.pokemon.FavoriteDetails
import com.example.application.ui.screens.pokemon.FavoriteEntryViewModel
import com.example.application.ui.screens.pokemon.PokemonDetailUiState
import kotlinx.coroutines.launch

@Composable
fun PokemonDetailScreen(
    pokemonDetailUiState: PokemonDetailUiState,
    viewModel: FavoriteEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }

    when (pokemonDetailUiState) {
        is PokemonDetailUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is PokemonDetailUiState.Success -> {
            val pokemon = pokemonDetailUiState.pokemon

            LaunchedEffect(pokemon?.name) {
                if (pokemon != null) {
                    isFavorite = viewModel.isFavorite(pokemon.name)
                }
            }

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
                            pokemon = pokemon,
                            isFavorite = isFavorite,
                            onFavoriteClick = {
                                coroutineScope.launch {
                                    isFavorite = viewModel.isFavorite(pokemon.name)
                                    Log.d("PokemonDetailScreen", "Is favorite: $isFavorite")

                                    if (viewModel.isFavorite(pokemon.name)) {
                                        Log.d("PokemonDetailScreen", "Removing favorite")
                                        viewModel.getFavorite(pokemon.name)
                                            ?.let { FavoriteDetails(name = pokemon.name, id = it.id ) }
                                            ?.let { viewModel.updateUiState(it) }
                                        viewModel.removeFavorite()
                                    } else {
                                        Log.d("PokemonDetailScreen", "Saving favorite")
                                        viewModel.updateUiState(FavoriteDetails(name = pokemon.name,))
                                        viewModel.saveFavorite()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
        is PokemonDetailUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
        else -> {}
    }
}


@Composable
fun PokemonDetailCard(
    pokemon: PokemonResponse,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit
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
                Icon(
                    Icons.Rounded.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favorite),
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier
                        .clickable {
                            onFavoriteClick()
                        },
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