package com.lizwin.dog_app.common.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lizwin.dog_app.api_key.presentation.ApiKeyViewModel

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination: String = Destinations.ApiKey.route) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destinations.ApiKey.route) {
            val viewModel: ApiKeyViewModel = hiltViewModel()
            ApiKeyScreen(viewModel::onEvent)
        }
    }
}
