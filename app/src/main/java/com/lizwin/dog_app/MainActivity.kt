package com.lizwin.dog_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lizwin.dog_app.common.presentation.navigation.AppNavHost
import com.lizwin.dog_app.common.presentation.theme.DogApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogApplicationTheme {
                AppNavHost()
            }
        }
    }
}