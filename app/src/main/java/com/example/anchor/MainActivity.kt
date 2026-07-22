package com.example.anchor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.example.anchor.ui.Components.NotesScreen
import com.example.anchor.ui.theme.AnchorTheme
import androidx.compose.ui.tooling.preview.Preview
import com.example.anchor.ui.homepage.HomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnchorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // 2. This grabs BackgroundDark (0xFF161615) from your Theme globally
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("notes") { NotesScreen() }
                    }
            }
        }
    }
}}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreenPreview() {
    AnchorTheme {
        val fakeNavController = rememberNavController()
        HomeScreen(fakeNavController)
    }
}

