@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.individuala3.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResponsiveMasterDetailScreen() {
    val options = listOf("Overview", "Progress", "Goals", "Friends", "Settings")
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(topBar = { TopAppBar(title = { Text("Responsive Screen") }) }) { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            val isWide = maxWidth >= 700.dp

            if (!isWide) {
                // PHONE: single Column
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OptionsList(
                        options = options,
                        selectedIndex = selectedIndex,
                        onSelect = { selectedIndex = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    DetailPane(
                        title = options[selectedIndex],
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                // TABLET/LANDSCAPE: two-pane Row
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Left pane
                    Card(
                        modifier = Modifier
                            .weight(0.35f) // REQUIRED weight allocation
                            .fillMaxHeight()
                    ) {
                        OptionsList(
                            options = options,
                            selectedIndex = selectedIndex,
                            onSelect = { selectedIndex = it },
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Right pane: Box + Column mix
                    Box(
                        modifier = Modifier
                            .weight(0.65f)
                            .fillMaxHeight()
                    ) {
                        DetailPane(
                            title = options[selectedIndex],
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OptionsList(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(options) { index, item ->
            ListItem(
                headlineContent = { Text(item) },
                supportingContent = { Text(if (index == selectedIndex) "Selected" else "Tap to view") },
                modifier = Modifier,
                colors = if (index == selectedIndex) {
                    ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                } else ListItemDefaults.colors()
            )
            Divider()
        }
    }
}

@Composable
private fun DetailPane(title: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            AssistChip(onClick = {}, label = { Text("Tip") })
            Text(
                "This pane demonstrates a detail view. On wide screens it sits beside the options list.",
                style = MaterialTheme.typography.bodyMedium
            )
            Button(onClick = {}) { Text("Action") }
        }
    }
}