package com.example.individuala3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.individuala3.ui.screens.HomeScreen
import com.example.individuala3.ui.screens.ProfileScreen
import com.example.individuala3.ui.screens.ResponsiveScreen
import com.example.individuala3.ui.screens.SettingsScreen
import com.example.individuala3.ui.screens.TagBrowserScreen
import com.example.individuala3.ui.theme.IndividualA3Theme

private enum class AppRoute { Home, Q1, Q2, Q3, Q4 }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IndividualA3Theme {
                var route by remember { mutableStateOf(AppRoute.Home) }

                when (route) {
                    AppRoute.Home -> HomeScreen(
                        onGoQ1 = { route = AppRoute.Q1 },
                        onGoQ2 = { route = AppRoute.Q2 },
                        onGoQ3 = { route = AppRoute.Q3 },
                        onGoQ4 = { route = AppRoute.Q4 }
                    )

                    AppRoute.Q1 -> SettingsScreen(onBack = { route = AppRoute.Home })
                    AppRoute.Q2 -> ProfileScreen(onBack = { route = AppRoute.Home })
                    AppRoute.Q3 -> TagBrowserScreen(onBack = { route = AppRoute.Home })
                    AppRoute.Q4 -> ResponsiveScreen(onBack = { route = AppRoute.Home })
                }
            }
        }
    }
}