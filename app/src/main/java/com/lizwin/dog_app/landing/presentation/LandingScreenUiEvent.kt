package com.lizwin.dog_app.landing.presentation

sealed class LandingScreenUiEvent {
    object FetchDogData : LandingScreenUiEvent()
}