package com.lizwin.dog_app.landing.domain.model

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.domain.model.Dog

data class DogListResponseData(
    val status: ResponseStatus = ResponseStatus.SUCCESS,
    val response: List<Dog> = emptyList()
)