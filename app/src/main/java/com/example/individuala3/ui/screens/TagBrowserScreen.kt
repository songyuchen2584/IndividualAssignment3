@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.example.individuala3.ui.screens

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TagBrowserScreen() {
    val allTags = listOf(
        "Fitness", "Sleep", "Nutrition", "Mindfulness", "Focus", "Social",
        "Running", "Cycling", "Yoga", "Strength", "Study", "Work", "Music"
    )

    val selected = remember { mutableStateListOf<String>() }
    var onlyPopular by remember { mutableStateOf(false) }
    var sortByName by remember { mutableStateOf(true) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Tag Browser") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Browse tags", style = MaterialTheme.typography.titleMedium)

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val shownTags = allTags
                            .let { if (sortByName) it.sorted() else it }
                            .let { if (onlyPopular) it.take(7) else it }

                        shownTags.forEach { tag ->
                            val isSelected = tag in selected
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    if (isSelected) selected.remove(tag) else selected.add(tag)
                                },
                                label = { Text(tag) },
                                modifier = Modifier
                                    .then(
                                        if (isSelected) Modifier.border(
                                            1.dp,
                                            MaterialTheme.colorScheme.primary,
                                            RoundedCornerShape(16.dp)
                                        ) else Modifier
                                    )
                            )
                        }
                    }
                }
            }

            // Second section uses FlowColumn (filters that wrap vertically / multi-columns if space)
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Filters", style = MaterialTheme.typography.titleMedium)

                    FlowColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AssistChip(
                            onClick = { onlyPopular = !onlyPopular },
                            label = { Text(if (onlyPopular) "Popular: On" else "Popular: Off") }
                        )
                        AssistChip(
                            onClick = { sortByName = !sortByName },
                            label = { Text(if (sortByName) "Sort: A→Z" else "Sort: Original") }
                        )
                        Button(onClick = { selected.clear() }) { Text("Clear selected") }
                    }
                }
            }

            // Selected tags area (updates live)
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Selected tags", style = MaterialTheme.typography.titleMedium)
                    if (selected.isEmpty()) {
                        Text("Tap chips above to select tags.", style = MaterialTheme.typography.bodySmall)
                    } else {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            selected.forEach { tag ->
                                AssistChip(onClick = { selected.remove(tag) }, label = { Text(tag) })
                            }
                        }
                    }
                }
            }
        }
    }
}