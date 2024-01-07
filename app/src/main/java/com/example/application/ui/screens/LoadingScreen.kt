package com.example.application.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.application.R

/**
 * Screen that displays an error message.
 *
 * @param modifier Modifier to be applied to the layout.
 */
@Composable
fun LoadingScreen(modifier: Modifier) {
    Text(text = stringResource(R.string.loading), modifier = modifier)
}