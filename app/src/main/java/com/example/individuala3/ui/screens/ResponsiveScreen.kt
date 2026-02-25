package com.example.individuala3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveScreen(onBack: () -> Unit) {
    val options = remember {
        listOf(
            "Overview" to "Responsive layout using BoxWithConstraints.",
            "Stats" to "Left pane list + right detail area.",
            "Settings" to "Uses weight and fillMaxHeight for panes.",
            "Help" to "LazyColumn demonstrates scroll behavior."
        )
    }
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Q4 — Responsive") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { inner ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
        ) {
            val isWide = maxWidth >= 600.dp

            if (!isWide) {
                // Phone: single Column
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Phone layout", style = MaterialTheme.typography.titleMedium)

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(options.indices.toList()) { i ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                            ) {
                                ListItem(
                                    headlineContent = { Text(options[i].first) },
                                    supportingContent = { Text(options[i].second) }
                                )
                            }
                        }
                    }
                }
            } else {
                // Wide: Row with two panes
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    // NavigationRail satisfies requirement
                    NavigationRail(modifier = Modifier.fillMaxHeight()) {
                        options.forEachIndexed { i, pair ->
                            NavigationRailItem(
                                selected = selectedIndex == i,
                                onClick = { selectedIndex = i },
                                icon = {},
                                label = { Text(pair.first) }
                            )
                        }
                    }

                    // Left pane: options list
                    Column(
                        modifier = Modifier
                            .weight(0.9f) // weight between panes
                            .fillMaxHeight()
                            .padding(start = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Options", style = MaterialTheme.typography.titleMedium)
                        Divider()

                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(options.indices.toList()) { i ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp)
                                ) {
                                    ListItem(
                                        headlineContent = { Text(options[i].first) },
                                        supportingContent = { Text(options[i].second) }
                                    )
                                }
                            }
                        }
                    }

                    // Right pane: detail content (Box + Column)
                    Box(
                        modifier = Modifier
                            .weight(1.4f)
                            .fillMaxHeight()
                            .padding(start = 12.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = options[selectedIndex].first,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Text(
                                text = options[selectedIndex].second,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Card(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(14.dp)) {
                                    Text("Detail panel", style = MaterialTheme.typography.titleMedium)
                                    Text(
                                        "Box + Column detail area for wide screens.",
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}