package com.example.application

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.application.screens.home.HomeScreen
import com.example.application.screens.pokemon.FavoritePokemonsScreen
import com.example.application.screens.pokemon.PokemonByGenerationScreen
import com.example.application.screens.pokemon.PokemonByTypeScreen
import com.example.application.screens.pokemon.PokemonDetailScreen
import com.example.application.screens.profile.ProfileScreen
import com.example.application.screens.utils.PokeTrackerNavigationType

enum class PokeTrackerScreen(@StringRes val title: Int, val route: String) {
    Home(R.string.home, "home"),
    Favorites(R.string.favorites, "favorites"),
    Profile(R.string.profile, "profile"),
    Detail(R.string.detail, "detail"),
    PokemonByType(R.string.pokemonByType, "type"),
    PokemonByGeneration(R.string.pokemonByGeneration, "generation"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeTrackerApp(
    navigationType: PokeTrackerNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PokeTrackerScreen.values().find {
        it.route.equals(
            backStackEntry?.destination?.route ?: PokeTrackerScreen.Home.route,
            ignoreCase = true,
        )
    } ?: PokeTrackerScreen.Home
    var selectedType by remember { mutableStateOf<String?>(null) }
    var selectedGeneration by remember { mutableStateOf<String?>(null) }
    var selectedPokemon by remember { mutableStateOf<String?>(null) }

    when (navigationType) {
        PokeTrackerNavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                topBar = {
                    TopNavigation(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() },
                    )
                },
                bottomBar = {
                    BottomNavigation()
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = PokeTrackerScreen.Home.route,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    composable(PokeTrackerScreen.Home.route) {
                        HomeScreen(
                            onTypeClicked = { type ->
                                selectedType = type
                                navController.navigate(PokeTrackerScreen.PokemonByType.route)
                            },
                            onGenerationClicked = { generation ->
                                selectedGeneration = generation
                                navController.navigate(PokeTrackerScreen.PokemonByGeneration.route)
                            },
                        )
                    }
                    composable(PokeTrackerScreen.Favorites.route) {
                        FavoritePokemonsScreen()
                    }
                    composable(PokeTrackerScreen.Profile.route) {
                        ProfileScreen()
                    }
                    composable(PokeTrackerScreen.Detail.route) {
                        PokemonDetailScreen(selectedPokemon)
                    }
                    composable(PokeTrackerScreen.PokemonByType.route) {
                        PokemonByTypeScreen(
                            selectedType,
                            onSelectPokemon = { pokemon ->
                                selectedPokemon = pokemon
                                navController.navigate(PokeTrackerScreen.Detail.route)
                            },
                        )
                    }
                    composable(PokeTrackerScreen.PokemonByGeneration.route) {
                        PokemonByGenerationScreen(
                            selectedGeneration,
                            onSelectPokemon = { pokemon ->
                                selectedPokemon = pokemon
                                navController.navigate(PokeTrackerScreen.Detail.route)
                            },
                        )
                    }
                }
            }
        }
        PokeTrackerNavigationType.NAVIGATION_RAIL -> {
            Surface(color = MaterialTheme.colorScheme.background) {
                Row {
                    PokeTrackerNavigationRail()
                    HomeScreen(onGenerationClicked = { generation ->
                        selectedType = generation
                        navController.navigate(PokeTrackerScreen.PokemonByGeneration.route)
                    })
                }
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("top app bar")
                        },
                    )
                },
                bottomBar = {
                    BottomNavigation()
                },
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    onGenerationClicked = { generation ->
                        selectedType = generation
                        navController.navigate(PokeTrackerScreen.PokemonByGeneration.route)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigation(
    currentScreen: PokeTrackerScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                    )
                }
            }
        },
    )
}

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home),
                )
            },
            selected = true,
            onClick = {},
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_favorites),
                )
            },
            selected = false,
            onClick = {},
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile),
                )
            },
            selected = false,
            onClick = {},
        )
    }
}

@Composable
fun PokeTrackerNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_favorites))
                },
                selected = false,
                onClick = {},
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {},
            )
        }
    }
}
