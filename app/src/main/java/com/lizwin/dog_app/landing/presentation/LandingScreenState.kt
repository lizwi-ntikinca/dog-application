package com.lizwin.dog_app.landing.presentation

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.domain.model.Dog

data class LandingScreenState(
    val status: ResponseStatus? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val dogList: List<Dog> = emptyList(),
    val filteredDogList: List<Dog> = emptyList(),
    val errorMessage: String = String()
)