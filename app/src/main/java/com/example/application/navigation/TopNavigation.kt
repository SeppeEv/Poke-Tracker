package com.example.application.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.application.R
import com.example.application.ui.PokeTrackerScreen

/**
 * Top app bar for the app.
 *
 * @param currentScreen The currently selected screen.
 * @param canNavigateBack Whether or not the user can navigate back.
 * @param navigateUp Called when the user wants to navigate up.
 * @param isStartDestination Whether or not the current screen is the start destination.
 * @param modifier Modifier for styling.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigation(
    currentScreen: PokeTrackerScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    isStartDestination: Boolean,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack && !isStartDestination) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                    )
                }
            }
        },
    )
}
