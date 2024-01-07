package com.example.application.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.application.R
import com.example.application.navigation.BottomNavigation
import com.example.application.navigation.PokeTrackerNavigationRail
import com.example.application.navigation.TopNavigation
import com.example.application.ui.screens.home.HomeScreen
import com.example.application.ui.screens.home.HomeViewModel
import com.example.application.ui.screens.pokemon.FavoritePokemonsScreen
import com.example.application.ui.screens.pokemon.PokemonByGenerationScreen
import com.example.application.ui.screens.pokemon.PokemonByGenerationViewModel
import com.example.application.ui.screens.pokemon.PokemonByTypeScreen
import com.example.application.ui.screens.pokemon.PokemonByTypeViewModel
import com.example.application.ui.screens.pokemon.PokemonDetailScreen
import com.example.application.ui.screens.profile.ProfileScreen
import com.example.application.ui.screens.utils.PokeTrackerNavigationType

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
                        isStartDestination = navController.currentDestination?.route == PokeTrackerScreen.Home.route,
                    )
                },
                bottomBar = {
                    BottomNavigation(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                    )
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = PokeTrackerScreen.Home.route,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    composable(PokeTrackerScreen.Home.route) {
                        val homeViewModel: HomeViewModel = viewModel()
                        HomeScreen(
                            homeUiState = homeViewModel.homeUiState,
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
                        val pokemonByTypeViewModel: PokemonByTypeViewModel = viewModel()
                        PokemonByTypeScreen(
                            /*pokeTrackerUiState = pokemonByTypeViewModel.pokeTrackerUiState,*/
                            pokeUiState = pokemonByTypeViewModel.pokeUiStateTest,
                            selectedType = selectedType,
                        ) { pokemon ->
                            selectedPokemon = pokemon
                            navController.navigate(PokeTrackerScreen.Detail.route)
                        }
                    }
                    composable(PokeTrackerScreen.PokemonByGeneration.route) {
                        val generationViewModel: PokemonByGenerationViewModel = viewModel()
                        PokemonByGenerationScreen(
                            generationUiState = generationViewModel.generationUiState,
                        ) { pokemon ->
                            selectedPokemon = pokemon
                            navController.navigate(PokeTrackerScreen.Detail.route)
                        }
                    }
                }
            }
        }
        PokeTrackerNavigationType.NAVIGATION_RAIL -> {
            Surface(color = MaterialTheme.colorScheme.background) {
                Row {
                    PokeTrackerNavigationRail(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                        navigateUp = { navController.navigateUp() },
                    )
                    Scaffold { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = PokeTrackerScreen.Home.route,
                            modifier = Modifier.padding(innerPadding),
                        ) {
                            composable(PokeTrackerScreen.Home.route) {
                                val homeViewModel: HomeViewModel = viewModel()
                                HomeScreen(
                                    homeUiState = homeViewModel.homeUiState,
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
                                val pokemonByTypeViewModel: PokemonByTypeViewModel = viewModel()
                                PokemonByTypeScreen(
                                    /*pokeTrackerUiState = pokemonByTypeViewModel.pokeTrackerUiState,*/
                                    pokeUiState = pokemonByTypeViewModel.pokeUiStateTest,
                                    selectedType = selectedType,
                                ) { pokemon ->
                                    selectedPokemon = pokemon
                                    navController.navigate(PokeTrackerScreen.Detail.route)
                                }
                            }
                            composable(PokeTrackerScreen.PokemonByGeneration.route) {
                                val generationViewModel: PokemonByGenerationViewModel = viewModel()
                                PokemonByGenerationScreen(
                                    generationUiState = generationViewModel.generationUiState,
                                ) { pokemon ->
                                    selectedPokemon = pokemon
                                    navController.navigate(PokeTrackerScreen.Detail.route)
                                }
                            }
                        }
                    }
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
                    BottomNavigation(
                        selectedDestination = navController.currentDestination,
                        onTabPressed = { node: String -> navController.navigate(node) },
                    )
                },
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = PokeTrackerScreen.Home.route,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    composable(PokeTrackerScreen.Home.route) {
                        val homeViewModel: HomeViewModel = viewModel()
                        HomeScreen(
                            homeUiState = homeViewModel.homeUiState,
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
                        val pokemonByTypeViewModel: PokemonByTypeViewModel = viewModel()
                        PokemonByTypeScreen(
                            /*pokeTrackerUiState = pokemonByTypeViewModel.pokeTrackerUiState,*/
                            pokeUiState = pokemonByTypeViewModel.pokeUiStateTest,
                            selectedType = selectedType,
                        ) { pokemon ->
                            selectedPokemon = pokemon
                            navController.navigate(PokeTrackerScreen.Detail.route)
                        }
                    }
                    composable(PokeTrackerScreen.PokemonByGeneration.route) {
                        val generationViewModel: PokemonByGenerationViewModel = viewModel()
                        PokemonByGenerationScreen(
                            generationUiState = generationViewModel.generationUiState,
                        ) { pokemon ->
                            selectedPokemon = pokemon
                            navController.navigate(PokeTrackerScreen.Detail.route)
                        }
                    }
                }
            }
        }
    }
}
