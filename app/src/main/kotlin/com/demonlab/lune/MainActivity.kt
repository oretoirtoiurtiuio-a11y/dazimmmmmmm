package com.demonlab.lune

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.demonlab.lune.ui.MainScreen
import com.demonlab.lune.ui.theme.DazaiPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DazaiPlayerTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0) // Handle insets manually for full control
    ) { innerPadding ->
        MainScreen(modifier = Modifier.padding(innerPadding))
    }
}
