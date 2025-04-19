package com.lizwin.dog_app.landing.presentation

sealed class LandingScreenUiEvent {
    object FetchDogData : LandingScreenUiEvent()
    data class Search(val query: String) : LandingScreenUiEvent()
}