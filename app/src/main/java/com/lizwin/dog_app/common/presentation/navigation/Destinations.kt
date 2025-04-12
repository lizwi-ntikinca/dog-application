package com.lizwin.dog_app.common.presentation.navigation

sealed class Destinations(val route: String) {
    data object ApiKey : Destinations(Routes.API_KEY)
}