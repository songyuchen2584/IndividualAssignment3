package com.example.individuala3.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun TagBrowserScreen(onBack: () -> Unit) {
    val tags = listOf(
        "Compose", "Material3", "Kotlin", "Android", "UI", "State",
        "Layouts", "Box", "Row", "Column", "Chips", "Responsive"
    )
    val selected = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Q3 — Tag Browser") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("All Tags", style = MaterialTheme.typography.titleMedium)

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        tags.forEach { tag ->
                            val isSelected = tag in selected
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    if (isSelected) selected.remove(tag) else selected.add(tag)
                                },
                                label = { Text(tag) },
                                modifier = Modifier.border(
                                    width = if (isSelected) 1.dp else 0.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = MaterialTheme.shapes.large
                                )
                            )
                        }
                    }

                    Divider()

                    Text("Selected Tags", style = MaterialTheme.typography.titleMedium)

                    FlowColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        maxItemsInEachColumn = 4
                    ) {
                        if (selected.isEmpty()) {
                            Text(
                                "Tap chips to select",
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        } else {
                            selected.forEach { tag ->
                                AssistChip(
                                    onClick = { selected.remove(tag) },
                                    label = { Text(tag) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}