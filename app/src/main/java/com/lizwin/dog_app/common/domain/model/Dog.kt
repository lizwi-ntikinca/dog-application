package com.lizwin.dog_app.common.domain.model

data class Dog(
    val breeds: List<Breed> = emptyList(),
    val id: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)
