package com.lizwin.dog_app.landing.domain.repository

import com.lizwin.dog_app.landing.domain.model.DogListResponseData

interface DogListRepository {
    suspend fun getAllApprovedDogImages() : DogListResponseData
}