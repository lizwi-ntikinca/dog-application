package com.lizwin.dog_app.landing.domain.model

import com.lizwin.dog_app.common.data.network.ResponseStatus

data class DogListResponseData(
    val status: ResponseStatus = ResponseStatus.SUCCESS,
    val response: List<Dog> = emptyList()
)

data class Dog(
    val breeds: List<Breed>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

data class Breed(
    val weight: Weight,
    val height: Height,
    val id: Int,
    val name: String,
    val countryCode: String,
    val bredFor: String,
    val breedGroup: String,
    val lifeSpan: String,
    val temperament: String,
    val origin: String,
    val referenceImageId: String
)

data class Weight(val imperial: String, val metric: String)
data class Height(val imperial: String, val metric: String)