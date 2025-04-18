package com.lizwin.dog_app.dog_details.view_dog_details.domain.model

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.domain.model.Dog

data class DogDetailsResponse (
    val status: ResponseStatus = ResponseStatus.SUCCESS,
    val response: Dog = Dog()
)