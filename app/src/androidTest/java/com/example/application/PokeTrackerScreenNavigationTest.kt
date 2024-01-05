package com.example.application

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.application.ui.PokeTrackerApp
import com.example.application.ui.PokeTrackerScreen
import com.example.application.ui.screens.utils.PokeTrackerNavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokeTrackerScreenNavigationTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupPokeTrackerNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            PokeTrackerApp(PokeTrackerNavigationType.BOTTOM_NAVIGATION, navController = navController)
        }
    }

    @Test
    fun pokeTrackerNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(PokeTrackerScreen.Home.name)
    }

    @Test
    fun pokeTrackerNavHost_verifyBackNavigationNotShownOnHomeScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun pokeTrackerNavHost_clickFavoritePokemons_navigatesToFavoritePokemonsScreen() {
        composeTestRule.onNodeWithStringId(R.string.favorites)
            .performClick()
        navController.assertCurrentRouteName(PokeTrackerScreen.Favorites.name)
    }

    @Test
    fun pokeTrackerNavHost_clickHome_navigatesToHomeScreen() {
        composeTestRule.onNodeWithStringId(R.string.home)
            .performClick()
        navController.assertCurrentRouteName(PokeTrackerScreen.Home.name)
    }

    @Test
    fun pokeTrackerNavHost_clickProfile_navigatesToProfileScreen() {
        composeTestRule.onNodeWithStringId(R.string.profile)
            .performClick()
        navController.assertCurrentRouteName(PokeTrackerScreen.Profile.name)
    }

    @Test
    fun pokeTrackerNavHost_clickPokemonByType_navigatesToPokemonByTypeScreen() {
        navigateToPokemonByTypeScreen()
        navController.assertCurrentRouteName(PokeTrackerScreen.PokemonByType.name)
    }

    @Test
    fun pokeTrackerNavHost_clickPokemonByGeneration_navigatesToPokemonByGenerationScreen() {
        navigateToPokemonByGenerationScreen()
        navController.assertCurrentRouteName(PokeTrackerScreen.PokemonByGeneration.name)
    }

    @Test
    fun pokeTrackerNavHost_clickPokemonDetailViaGeneration_navigatesToPokemonDetailScreen() {
        navigateToPokemonDetailScreenViaGeneration()
        navController.assertCurrentRouteName(PokeTrackerScreen.Detail.name)
    }

    @Test
    fun pokeTrackerNavHost_clickPokemonDetailViaType_navigatesToPokemonDetailScreen() {
        navigateToPokemonDetailScreenViaType()
        navController.assertCurrentRouteName(PokeTrackerScreen.Detail.name)
    }

    @Test
    fun pokeTrackerNavHost_clickBackOnPokemonDetailScreen_navigatesToPokemonByGenerationScreen() {
        navigateToPokemonDetailScreenViaGeneration()
        performNavigateUp()
        navController.assertCurrentRouteName(PokeTrackerScreen.PokemonByGeneration.name)
    }

    @Test
    fun pokeTrackerNavHost_clickBackOnPokemonByGenerationScreen_navigatesToHomeScreen() {
        navigateToPokemonByGenerationScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(PokeTrackerScreen.Home.name)
    }

    @Test
    fun pokeTrackerNavHost_clickBackOnPokemonByTypeScreen_navigatesToHomeScreen() {
        navigateToPokemonByTypeScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(PokeTrackerScreen.Home.name)
    }

    @Test
    fun pokeTrackerNavHost_clickBackOnProfileScreen_navigatesToHomeScreen() {
        navigateToProfileScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(PokeTrackerScreen.Home.name)
    }

    @Test
    fun pokeTrackerNavHost_clickBackOnFavoritePokemonsScreen_navigatesToHomeScreen() {
        navigateToFavoritePokemonsScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(PokeTrackerScreen.Home.name)
    }

    private fun navigateToFavoritePokemonsScreen() {
        composeTestRule.onNodeWithStringId(R.string.favorites)
            .performClick()
    }

    private fun navigateToHomeScreen() {
        composeTestRule.onNodeWithStringId(R.string.home)
            .performClick()
    }

    private fun navigateToProfileScreen() {
        composeTestRule.onNodeWithStringId(R.string.profile)
            .performClick()
    }

    private fun navigateToPokemonByTypeScreen() {
        composeTestRule.onNodeWithTag("TypeElement")
            .performClick()
    }

    private fun navigateToPokemonByGenerationScreen() {
        composeTestRule.onNodeWithTag("GenerationCard")
            .performClick()
    }

    private fun navigateToPokemonDetailScreenViaGeneration() {
        navigateToPokemonByTypeScreen()
        composeTestRule.onNodeWithTag("PokemonCard")
            .performClick()
    }

    private fun navigateToPokemonDetailScreenViaType() {
        navigateToPokemonByGenerationScreen()
        composeTestRule.onNodeWithTag("PokemonCard")
            .performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }
}
