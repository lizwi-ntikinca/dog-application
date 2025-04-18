package com.lizwin.dog_app.dog_details.view_dog_details.data.repository

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.data.remote.TheDogApi
import com.lizwin.dog_app.dog_details.view_dog_details.data.model.toDogResponse
import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse
import com.lizwin.dog_app.dog_details.view_dog_details.domain.repository.ViewDogDetailsRepository
import javax.inject.Inject

class ViewDogDetailsRepositoryImpl @Inject constructor(
    private val theDogApi: TheDogApi
) : ViewDogDetailsRepository{
    override suspend fun viewDogDetails(id: String): DogDetailsResponse {
        return try {
            theDogApi.getDogDetails(id).toDogResponse()
        } catch (e: Exception) {
            e.printStackTrace()
            DogDetailsResponse(
                status = ResponseStatus.FAILED
            )
        }
    }
}