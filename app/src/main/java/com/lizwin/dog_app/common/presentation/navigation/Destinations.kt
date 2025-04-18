package com.lizwin.dog_app.common.presentation.navigation

import com.lizwin.dog_app.common.presentation.navigation.Routes.ID

sealed class Destinations(val route: String) {
    data object ApiKey : Destinations(Routes.API_KEY)
    data object LandingScreen : Destinations(Routes.LANDING)
    data object DetailsScreen : Destinations("${Routes.DOG_DETAILS}/{$ID}")
}