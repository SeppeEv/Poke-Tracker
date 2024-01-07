package com.example.application.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.example.application.R
import com.example.application.ui.PokeTrackerScreen

/**
 * Bottom navigation bar for the app.
 *
 * @param selectedDestination The currently selected destination.
 * @param onTabPressed Called when a tab is pressed.
 * @param modifier Modifier for styling.
 */
@Composable
fun BottomNavigation(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        NavigationBarItem(
            selected = selectedDestination?.route == PokeTrackerScreen.Home.route,
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
            onClick = { onTabPressed(PokeTrackerScreen.Home.route) },
        )
        NavigationBarItem(
            selected = selectedDestination?.route == PokeTrackerScreen.Favorites.route,
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_random),
                )
            },
            onClick = { onTabPressed(PokeTrackerScreen.Favorites.route) },
        )
        NavigationBarItem(
            selected = selectedDestination?.route == PokeTrackerScreen.Profile.route,
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_about),
                )
            },
            onClick = { onTabPressed(PokeTrackerScreen.Profile.route) },
        )
    }
}