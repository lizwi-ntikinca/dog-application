package com.lizwin.dog_app.landing.data.model

import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.lizwin.dog_app.landing.domain.model.Dog
import com.lizwin.dog_app.landing.domain.model.Breed
import com.lizwin.dog_app.landing.domain.model.Height
import com.lizwin.dog_app.landing.domain.model.Weight

@Serializable
data class DogResponseDTO(
    val breeds: List<BreedDTO>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

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

@Serializable
data class WeightDTO(
    val imperial: String = "",
    val metric: String = ""
)

@Serializable
data class HeightDTO(
    val imperial: String = "",
    val metric: String = ""
)

fun List<DogResponseDTO>.toDogResponse(): DogListResponseData {
    return DogListResponseData(
        response = map { dto ->
            Dog(
                breeds = dto.breeds.map { breed ->
                    Breed(
                        weight = Weight(breed.weight.imperial, breed.weight.metric),
                        height = Height(breed.height.imperial, breed.height.metric),
                        id = breed.id,
                        name = breed.name,
                        countryCode = breed.country_code.orEmpty(),
                        bredFor = breed.bred_for.orEmpty(),
                        breedGroup = breed.breed_group.orEmpty(),
                        lifeSpan = breed.life_span,
                        temperament = breed.temperament.orEmpty(),
                        origin = breed.origin.orEmpty(),
                        referenceImageId = breed.reference_image_id.orEmpty()
                    )
                },
                id = dto.id,
                url = dto.url,
                width = dto.width,
                height = dto.height
            )
        }
    )
}