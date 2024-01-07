package com.example.application.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.application.R

/**
 * Bottom navigation bar for the app.
 *
 * @param selectedDestination The currently selected destination.
 * @param onTabPressed Called when a tab is pressed.
 * @param modifier Modifier for styling.
 */
@Composable
fun PokeTrackerNavigationRail(
    selectedDestination: NavDestination?,
    onTabPressed: (String) -> Unit,
    navigateUp: () -> Boolean,
    modifier: Modifier = Modifier,
) {
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
                selected = selectedDestination?.route == "home",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                onClick = { onTabPressed("Home") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = selectedDestination?.route == "favorites",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_favorites))
                },
                onClick = { onTabPressed("Favorites") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = selectedDestination?.route == "profile",
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                onClick = { onTabPressed("Profile") },
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                selected = selectedDestination?.route == "back",
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(stringResource(R.string.back_button))
                },
                onClick = { navigateUp() },
            )
        }
    }
}
