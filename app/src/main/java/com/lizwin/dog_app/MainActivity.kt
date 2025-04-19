package com.lizwin.dog_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.lizwin.dog_app.common.data.security.SecureStorage
import com.lizwin.dog_app.common.presentation.navigation.AppNavHost
import com.lizwin.dog_app.common.presentation.navigation.Destinations
import com.lizwin.dog_app.common.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storedApiKey = SecureStorage.getApiKey(this)
        var startDestination: Destinations = Destinations.LandingScreen


        if (storedApiKey == null) {
            startDestination = Destinations.ApiKey
        }
        enableEdgeToEdge()
        setContent {
            val backDispatcher = remember { onBackPressedDispatcher }

            AppTheme {
                AppNavHost(
                    startDestination = startDestination,
                    onNavigateBack = { backDispatcher.onBackPressed() }
                )
            }
        }
    }
}