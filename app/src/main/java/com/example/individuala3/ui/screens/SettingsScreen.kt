@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.individuala3.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var analyticsEnabled by remember { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(0.35f) }
    var compactMode by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Settings, contentDescription = null) }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Preferences", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    Divider()

                    SettingRow(
                        icon = { Icon(Icons.Default.Notifications, contentDescription = null) },
                        label = "Notifications",
                        supporting = "Get reminders and updates",
                        control = {
                            Switch(
                                checked = notificationsEnabled,
                                onCheckedChange = { notificationsEnabled = it }
                            )
                        },
                        onRowClick = { notificationsEnabled = !notificationsEnabled }
                    )

                    Divider()

                    SettingRow(
                        icon = { Icon(Icons.Default.Security, contentDescription = null) },
                        label = "Analytics",
                        supporting = "Help improve the app (anonymous)",
                        control = {
                            Checkbox(
                                checked = analyticsEnabled,
                                onCheckedChange = { analyticsEnabled = it }
                            )
                        },
                        onRowClick = { analyticsEnabled = !analyticsEnabled }
                    )

                    Divider()

                    // Slider row (still a Row with left text column + right control)
                    SettingRow(
                        icon = { Text("🔊") },
                        label = "Notification volume",
                        supporting = "Adjust reminder loudness",
                        control = {
                            Slider(
                                value = volume,
                                onValueChange = { volume = it },
                                modifier = Modifier.widthIn(min = 140.dp, max = 220.dp)
                            )
                        }
                    )

                    Divider()

                    SettingRow(
                        icon = { Text("🧩") },
                        label = "Compact mode",
                        supporting = "Smaller cards and spacing",
                        control = {
                            Button(
                                onClick = { compactMode = !compactMode },
                                modifier = Modifier.heightIn(min = 40.dp)
                            ) { Text(if (compactMode) "On" else "Off") }
                        },
                        onRowClick = { compactMode = !compactMode }
                    )
                }
            }

            // Extra M3 components + modifier requirements (chip, clickable, border, background, clip)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                    .clickable {
                        // Example action
                    }
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Quick actions", style = MaterialTheme.typography.titleSmall)
                    Text("Tap a chip to try something", style = MaterialTheme.typography.bodySmall)
                }
                AssistChip(
                    onClick = {
                        // Example snackbar usage
                        // launch in coroutine scope
                    },
                    label = { Text("Reset") }
                )
            }
        }
    }
}

@Composable
private fun SettingRow(
    icon: @Composable () -> Unit,
    label: String,
    supporting: String,
    control: @Composable () -> Unit,
    onRowClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .then(if (onRowClick != null) Modifier.clickable { onRowClick() } else Modifier)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .sizeIn(minWidth = 36.dp, minHeight = 36.dp)
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.Center
        ) { icon() }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f) // KEY: prevents truncation + pushes control right
        ) {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            Text(supporting, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }

        Spacer(Modifier.width(12.dp))

        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            control()
        }
    }
}