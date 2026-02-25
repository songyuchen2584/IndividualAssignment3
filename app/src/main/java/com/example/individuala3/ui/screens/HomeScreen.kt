package com.example.individuala3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onGoQ1: () -> Unit,
    onGoQ2: () -> Unit,
    onGoQ3: () -> Unit,
    onGoQ4: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Individual Assignment 3") }) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Pick a screen:", style = MaterialTheme.typography.titleMedium)

            NavCard(
                title = "Q1 — Settings Screen",
                subtitle = "Row/Column + modifiers + M3 components",
                onClick = onGoQ1
            )
            NavCard(
                title = "Q2 — Profile Header",
                subtitle = "Box layering + overlay card",
                onClick = onGoQ2
            )
            NavCard(
                title = "Q3 — Tag Browser",
                subtitle = "FlowRow / FlowColumn + selected state",
                onClick = onGoQ3
            )
            NavCard(
                title = "Q4 — Responsive Layout",
                subtitle = "Phone vs tablet layout switch",
                onClick = onGoQ4
            )
        }
    }
}

@Composable
private fun NavCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Button(onClick = onClick) { Text("Open") }
        }
    }
}