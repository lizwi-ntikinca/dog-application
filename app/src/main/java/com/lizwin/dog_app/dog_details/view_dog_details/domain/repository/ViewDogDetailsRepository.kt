package com.lizwin.dog_app.dog_details.view_dog_details.domain.repository

import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse

interface ViewDogDetailsRepository {
    suspend fun viewDogDetails(id: String) : DogDetailsResponse
}