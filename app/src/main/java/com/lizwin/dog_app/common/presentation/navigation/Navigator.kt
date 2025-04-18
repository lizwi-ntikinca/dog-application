package com.lizwin.dog_app.common.presentation.navigation

interface Navigator {
    fun navigateToHomeScreen()
    fun navigateBack()
    fun navigateToDogDetailsScreen(id: String)
}