package com.example.application

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.application.screens.home.HomeScreen
import com.example.application.screens.utils.PokeTrackerNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeTrackerApp(navigationType: PokeTrackerNavigationType) {
    when (navigationType) {
        PokeTrackerNavigationType.BOTTOM_NAVIGATION -> {
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
            ) { padding ->
                HomeScreen(Modifier.padding(padding))
            }
        }
        PokeTrackerNavigationType.NAVIGATION_RAIL -> {
            Surface(color = MaterialTheme.colorScheme.background) {
                Row {
                    PokeTrackerNavigationRail()
                    HomeScreen()
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
            ) { padding ->
                HomeScreen(Modifier.padding(padding))
            }
        }
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
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
            )
            content()
        }
    }
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
