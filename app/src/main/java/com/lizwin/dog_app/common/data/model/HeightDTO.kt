package com.lizwin.dog_app.common.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HeightDTO(
    val imperial: String = "",
    val metric: String = ""
)
