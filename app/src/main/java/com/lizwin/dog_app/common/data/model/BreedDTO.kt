package com.lizwin.dog_app.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedDTO(
    val weight: WeightDTO = WeightDTO(),
    val height: HeightDTO = HeightDTO(),
    val id: Int = 0,
    val name: String = "",
    @SerialName("country_code")
    val country_code: String? = "",
    @SerialName("bred_for")
    val bred_for: String? = "",
    @SerialName("breed_group")
    val breed_group: String? = "",
    @SerialName("life_span")
    val life_span: String = "",
    val temperament: String? = "",
    val origin: String? = "",
    @SerialName("reference_image_id")
    val reference_image_id: String? = ""
)
