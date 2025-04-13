package com.lizwin.dog_app.landing.data.repository

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.data.remote.TheDogApi
import com.lizwin.dog_app.landing.data.model.toDogResponse
import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import com.lizwin.dog_app.landing.domain.repository.DogListRepository
import javax.inject.Inject

class DogListRepositoryImpl @Inject constructor(
    private val theDogApi: TheDogApi
) : DogListRepository{
    override suspend fun getAllApprovedDogImages(): DogListResponseData {
        return try {
            theDogApi.searchAllApprovedImages().toDogResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            DogListResponseData(
                status = ResponseStatus.FAILED,
                response = emptyList()
            )
        }
    }
}