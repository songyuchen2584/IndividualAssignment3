package com.example.individuala3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    val snack = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var notifications by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }
    var autoSync by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Q1 — Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = { scope.launch { snack.showSnackbar("Synced") } }
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Sync")
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snack) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Preferences", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.padding(top = 8.dp))

                    SettingRow(
                        label = "Notifications",
                        supporting = "Get updates and reminders",
                        control = {
                            Switch(
                                checked = notifications,
                                onCheckedChange = { notifications = it }
                            )
                        },
                        onRowClick = { notifications = !notifications }
                    )

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    SettingRow(
                        label = "Dark mode",
                        supporting = "Use a darker color scheme",
                        control = {
                            Switch(
                                checked = darkMode,
                                onCheckedChange = { darkMode = it }
                            )
                        },
                        onRowClick = { darkMode = !darkMode }
                    )

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    SettingRow(
                        label = "Auto sync",
                        supporting = "Sync over Wi-Fi automatically",
                        control = {
                            Switch(
                                checked = autoSync,
                                onCheckedChange = { autoSync = it }
                            )
                        },
                        onRowClick = { autoSync = !autoSync }
                    )
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                ListItem(
                    headlineContent = { Text("About") },
                    supportingContent = { Text("Minimal Compose + Material 3 UI") }
                )
            }
        }
    }
}

@Composable
private fun SettingRow(
    label: String,
    supporting: String,
    control: @Composable () -> Unit,
    onRowClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp) // heightIn requirement
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.shapes.medium) // border
            .background(MaterialTheme.colorScheme.surface) // background
            .clickable { onRowClick() } // clickable
            .padding(horizontal = 12.dp, vertical = 10.dp), // padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f) // weight requirement (prevents truncation)
                .padding(end = 12.dp)
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = supporting,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Row(
            modifier = Modifier
                .sizeIn(minWidth = 48.dp) // sizeIn requirement
                .align(Alignment.CenterVertically), // align requirement
            verticalAlignment = Alignment.CenterVertically
        ) {
            control()
        }
    }
}