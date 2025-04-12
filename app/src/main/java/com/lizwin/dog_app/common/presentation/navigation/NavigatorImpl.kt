package com.lizwin.dog_app.common.presentation.navigation

import androidx.navigation.NavController

class NavigatorImpl(
    private val navController: NavController,
    private val onNavigateBack: () -> Unit
) : Navigator {
    override fun navigateToHomeScreen() {
        navController.navigate(Destinations.LandingScreen.route)
    }

    override fun navigateBack() {
        onNavigateBack()
    }
}
