package com.lizwin.dog_app.common.domain.model

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
