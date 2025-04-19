package com.lizwin.dog_app.dog_details.view_dog_details.presentation

sealed class DogDetailsScreenUiEvent {
    data class Load(val imgId: String) : DogDetailsScreenUiEvent()
}