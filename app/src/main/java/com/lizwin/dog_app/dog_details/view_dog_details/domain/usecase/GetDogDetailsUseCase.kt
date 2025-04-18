package com.lizwin.dog_app.dog_details.view_dog_details.domain.usecase

import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse
import com.lizwin.dog_app.dog_details.view_dog_details.domain.repository.ViewDogDetailsRepository
import javax.inject.Inject

class GetDogDetailsUseCase @Inject constructor(
    private val viewDogDetailsRepository: ViewDogDetailsRepository
) {
    suspend operator fun invoke(id: String) : DogDetailsResponse {
        return viewDogDetailsRepository.viewDogDetails(id)
    }
}