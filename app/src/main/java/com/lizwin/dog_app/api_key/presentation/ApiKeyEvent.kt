package com.lizwin.dog_app.api_key.presentation

sealed class ApiKeyEvent {
    data class OnDoneButtonClicked(val apiKey: String) : ApiKeyEvent()
}