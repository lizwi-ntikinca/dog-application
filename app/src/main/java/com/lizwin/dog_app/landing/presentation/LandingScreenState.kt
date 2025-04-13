package com.lizwin.dog_app.landing.presentation

import com.lizwin.dog_app.landing.domain.model.DogListResponseData

data class LandingScreenState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val dogData: DogListResponseData = DogListResponseData(),
    val errorMessage: String = String()
)