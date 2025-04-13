package com.lizwin.dog_app.landing.domain.usecase

import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import com.lizwin.dog_app.landing.domain.repository.DogListRepository
import javax.inject.Inject

class GetAllApprovedDogImagesUsecase @Inject constructor(
    private val repository: DogListRepository
) {
    suspend operator fun invoke() : DogListResponseData {
        return repository.getAllApprovedDogImages()
    }
}