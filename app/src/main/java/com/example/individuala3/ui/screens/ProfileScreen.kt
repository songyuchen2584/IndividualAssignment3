@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.individuala3.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import androidx.compose.ui.unit.dp

@Composable
fun ProfileHeaderOverlayScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Profile") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.TopCenter
            ) {
                // Avatar foreground
                Box(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .size(96.dp) // fixed size requirement
                        .clip(CircleShape) // REQUIRED
                        .background(MaterialTheme.colorScheme.surface)
                        .zIndex(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(44.dp))
                }
            }

            // Overlapping card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = (-36).dp) // REQUIRED overlap technique
                    .zIndex(3f),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp) // shadow/elevation requirement
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("SongYu Chen", style = MaterialTheme.typography.titleLarge)
                    Text("Boston, MA • Computer Science", style = MaterialTheme.typography.bodyMedium)

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AssistChip(onClick = {}, label = { Text("Student") })
                        AssistChip(onClick = {}, label = { Text("Android") })
                        AssistChip(onClick = {}, label = { Text("ML") })
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilledTonalButton(onClick = {}) { Text("Follow") }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit profile")
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // Extra content
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("About", style = MaterialTheme.typography.titleMedium)
                    Text("This screen demonstrates Box layering, avatar clipping, and a classic overlapping profile card layout.")
                }
            }
        }
    }
}