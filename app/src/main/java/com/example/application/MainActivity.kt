package com.example.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.application.screens.utils.PokeTrackerNavigationType
import com.example.application.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                val windowSize = calculateWindowSizeClass(this)
                when (windowSize.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        PokeTrackerApp(PokeTrackerNavigationType.BOTTOM_NAVIGATION)
                    }
                    WindowWidthSizeClass.Expanded -> {
                        PokeTrackerApp(PokeTrackerNavigationType.NAVIGATION_RAIL)
                    }
                    else -> {
                        PokeTrackerApp(PokeTrackerNavigationType.BOTTOM_NAVIGATION)
                    }
                }
            }
        }
    }
}
