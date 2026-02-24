package com.example.individuala3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    var screen by remember { mutableStateOf("menu") }

    when(screen) {

        "menu" -> Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text("Assignment 3", style = MaterialTheme.typography.headlineMedium)

            Button(onClick = { screen = "q1" }) {
                Text("Q1 Settings Screen")
            }

            Button(onClick = { screen = "q2" }) {
                Text("Q2 Profile Screen")
            }

            Button(onClick = { screen = "q3" }) {
                Text("Q3 Tag Browser")
            }

            Button(onClick = { screen = "q4" }) {
                Text("Q4 Responsive Screen")
            }
        }

        "q1" -> SettingsScreen()
        "q2" -> ProfileHeaderOverlayScreen()
        "q3" -> TagBrowserScreen()
        "q4" -> ResponsiveMasterDetailScreen()
    }
}