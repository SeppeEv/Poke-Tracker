package com.example.application.ui

import PokemonDetailScreen
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
import com.example.application.ui.screens.about.AboutScreen
import com.example.application.ui.screens.home.HomeScreen
import com.example.application.ui.screens.home.HomeViewModel
import com.example.application.ui.screens.pokemon.PokemonByGenerationScreen
import com.example.application.ui.screens.pokemon.PokemonByGenerationViewModel
import com.example.application.ui.screens.pokemon.PokemonByTypeScreen
import com.example.application.ui.screens.pokemon.PokemonByTypeViewModel
import com.example.application.ui.screens.pokemon.PokemonDetailViewModel
import com.example.application.ui.screens.pokemon.RandomPokemonsScreen
import com.example.application.ui.screens.pokemon.RandomPokemonsViewModel
import com.example.application.ui.screens.utils.PokeTrackerNavigationType

/**
 * The different screens available in the app.
 */
enum class PokeTrackerScreen(@StringRes val title: Int, val route: String) {
    Home(R.string.home, "home"),
    Favorites(R.string.random, "favorites"),
    Profile(R.string.profile, "profile"),
    Detail(R.string.detail, "detail/{pokemonName}"),
    PokemonByType(R.string.pokemonByType, "type/{typeId}"),
    PokemonByGeneration(R.string.pokemonByGeneration, "generation/{generationId}"),
}

/**
 * Entry point for the app.
 *
 * @param navigationType The type of navigation to use in the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeTrackerApp(
    navigationType: PokeTrackerNavigationType,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PokeTrackerScreen.entries.find {
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
                            onTypeClicked = { typeID ->
                                selectedType = typeID
                                navController.navigate("type/$typeID")
                            },
                            onGenerationClicked = { generationID ->
                                selectedGeneration = generationID
                                navController.navigate("generation/$generationID")
                            },
                        )
                    }
                    composable(PokeTrackerScreen.Favorites.route) {
                        val randomPokemonViewModel: RandomPokemonsViewModel = viewModel()
                        RandomPokemonsScreen(
                            randomPokemonUiState = randomPokemonViewModel.randomPokemonUiState,
                        )
                    }
                    composable(PokeTrackerScreen.Profile.route) {
                        AboutScreen()
                    }
                    composable(PokeTrackerScreen.Detail.route) {backStackEntry ->
                        val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                        val pokemonDetailViewModel: PokemonDetailViewModel = viewModel()

                        pokemonName?.let {
                            pokemonDetailViewModel.getPokemonDetail(it)
                        }

                        PokemonDetailScreen(
                            pokemonDetailUiState = pokemonDetailViewModel.pokemonDetailUiState,
                            )
                    }
                    composable(PokeTrackerScreen.PokemonByType.route) {backStackEntry ->
                        val typeId = backStackEntry.arguments?.getString("typeId")
                        val pokemonByTypeViewModel: PokemonByTypeViewModel = viewModel()

                        typeId?.let {
                            pokemonByTypeViewModel.getPokemonByType(it)
                        }

                        PokemonByTypeScreen(
                            typeUiState = pokemonByTypeViewModel.typeUiState,
                        ) { pokemon ->
                            selectedPokemon = pokemon
                            navController.navigate("detail/$pokemon")
                        }
                    }
                    composable(PokeTrackerScreen.PokemonByGeneration.route) { backStackEntry ->
                        // Extract generation ID from navigation arguments
                        val generationId = backStackEntry.arguments?.getString("generationId")?.toIntOrNull()
                        val pokemonByGenerationViewModel: PokemonByGenerationViewModel = viewModel()

                        // Fetch data for the generation if the ID is valid
                        generationId.let {
                            if (generationId != null) {
                                pokemonByGenerationViewModel.getPokemonByGeneration(generationId)
                            }
                        }

                        PokemonByGenerationScreen(
                            generationUiState = pokemonByGenerationViewModel.generationUiState,
                            onSelectPokemon = { pokemon ->
                                selectedPokemon = pokemon
                                navController.navigate("detail/$pokemon")
                            }
                        )
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
                                    onTypeClicked = { typeID ->
                                        selectedType = typeID
                                        navController.navigate("type/$typeID")
                                    },
                                    onGenerationClicked = { generationID ->
                                        selectedGeneration = generationID
                                        navController.navigate("generation/$generationID")
                                    },
                                )
                            }
                            composable(PokeTrackerScreen.Favorites.route) {
                                val randomPokemonViewModel: RandomPokemonsViewModel = viewModel()
                                RandomPokemonsScreen(
                                    randomPokemonUiState = randomPokemonViewModel.randomPokemonUiState,
                                )
                            }
                            composable(PokeTrackerScreen.Profile.route) {
                                AboutScreen()
                            }
                            composable(PokeTrackerScreen.Detail.route) {backStackEntry ->
                                val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                                val pokemonDetailViewModel: PokemonDetailViewModel = viewModel()

                                pokemonName?.let {
                                    pokemonDetailViewModel.getPokemonDetail(it)
                                }

                                PokemonDetailScreen(
                                    pokemonDetailUiState = pokemonDetailViewModel.pokemonDetailUiState,)
                            }
                            composable(PokeTrackerScreen.PokemonByType.route) {
                                val pokemonByTypeViewModel: PokemonByTypeViewModel = viewModel()
                                PokemonByTypeScreen(
                                    typeUiState = pokemonByTypeViewModel.typeUiState,
                                ) { pokemon ->
                                    selectedPokemon = pokemon
                                    navController.navigate("detail/$pokemon")
                                }
                            }
                            composable(PokeTrackerScreen.PokemonByGeneration.route) { backStackEntry ->
                                // Extract generation ID from navigation arguments
                                val generationId = backStackEntry.arguments?.getString("generationId")?.toIntOrNull() ?: 1

                                val pokemonByGenerationViewModel: PokemonByGenerationViewModel = viewModel()

                                // Fetch data for the generation if the ID is valid
                                generationId.let {
                                    pokemonByGenerationViewModel.getPokemonByGeneration(it)
                                }

                                PokemonByGenerationScreen(
                                    generationUiState = pokemonByGenerationViewModel.generationUiState,
                                    onSelectPokemon = { pokemon ->
                                        selectedPokemon = pokemon
                                        navController.navigate("detail/$pokemon")
                                    }
                                )
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
                            onTypeClicked = { typeID ->
                                selectedType = typeID
                                navController.navigate("type/$typeID")
                            },
                            onGenerationClicked = { generationID ->
                                selectedGeneration = generationID
                                navController.navigate("generation/$generationID")
                            },
                        )
                    }
                    composable(PokeTrackerScreen.Favorites.route) {
                        val randomPokemonViewModel: RandomPokemonsViewModel = viewModel()
                        RandomPokemonsScreen(
                            randomPokemonUiState = randomPokemonViewModel.randomPokemonUiState,
                        )
                    }
                    composable(PokeTrackerScreen.Profile.route) {
                        AboutScreen()
                    }
                    composable(PokeTrackerScreen.Detail.route) {backStackEntry ->
                        val pokemonName = backStackEntry.arguments?.getString("pokemonName")
                        val pokemonDetailViewModel: PokemonDetailViewModel = viewModel()

                        pokemonName?.let {
                            pokemonDetailViewModel.getPokemonDetail(it)
                        }

                        PokemonDetailScreen(
                            pokemonDetailUiState = pokemonDetailViewModel.pokemonDetailUiState,)
                    }
                    composable(PokeTrackerScreen.PokemonByType.route) {backStackEntry ->
                        val typeId = backStackEntry.arguments?.getString("typeId")
                        val pokemonByTypeViewModel: PokemonByTypeViewModel = viewModel()

                        typeId?.let {
                            pokemonByTypeViewModel.getPokemonByType(it)
                        }

                        PokemonByTypeScreen(
                            typeUiState = pokemonByTypeViewModel.typeUiState,
                        ) { pokemon ->
                            selectedPokemon = pokemon
                            navController.navigate("detail/$pokemon")
                        }
                    }
                    composable(PokeTrackerScreen.PokemonByGeneration.route) { backStackEntry ->
                        val generationId = backStackEntry.arguments?.getString("generationId")?.toIntOrNull() ?: 1
                        val pokemonByGenerationViewModel: PokemonByGenerationViewModel = viewModel()

                        generationId.let {
                            pokemonByGenerationViewModel.getPokemonByGeneration(it)
                        }

                        PokemonByGenerationScreen(
                            generationUiState = pokemonByGenerationViewModel.generationUiState,
                            onSelectPokemon = { pokemon ->
                                selectedPokemon = pokemon
                                navController.navigate("detail/$pokemon")
                            }
                        )
                    }

                }
            }
        }
    }
}
