package com.example.application.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.application.R

/**
 * Screen that displays an error message.
 *
 * @param modifier Modifier to be applied to the layout.

 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /*Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "",
        )*/
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}