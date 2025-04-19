package com.lizwin.dog_app.dog_details.view_dog_details.presentation

import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse

data class DogDetailsScreenState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val dogInformation: DogDetailsResponse = DogDetailsResponse(),
    val errorMessage: String = String()
)