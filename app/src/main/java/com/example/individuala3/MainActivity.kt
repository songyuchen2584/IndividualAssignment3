package com.example.individuala3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.individuala3.ui.theme.IndividualA3Theme
import com.example.individuala3.ui.screens.HomeScreen
import com.example.individuala3.ui.screens.SettingsScreen
import com.example.individuala3.ui.screens.ProfileHeaderOverlayScreen
import com.example.individuala3.ui.screens.TagBrowserScreen
import com.example.individuala3.ui.screens.ResponsiveMasterDetailScreen
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MaterialTheme {

                HomeScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IndividualA3Theme {
        Greeting("Android")
    }
}