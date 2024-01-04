package com.example.application.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.example.application.PokeTrackerScreen
import com.example.application.R

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
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_favorites),
                )
            },
            onClick = { onTabPressed(PokeTrackerScreen.Favorites.route) },
        )
        NavigationBarItem(
            selected = selectedDestination?.route == PokeTrackerScreen.Profile.route,
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
            onClick = { onTabPressed(PokeTrackerScreen.Profile.route) },
        )
    }
}