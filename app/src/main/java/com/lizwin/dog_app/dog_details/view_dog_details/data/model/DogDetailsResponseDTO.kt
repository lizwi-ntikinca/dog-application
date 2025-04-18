package com.lizwin.dog_app.dog_details.view_dog_details.data.model

import com.lizwin.dog_app.common.data.model.BreedDTO
import com.lizwin.dog_app.common.domain.model.Breed
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.common.domain.model.Height
import com.lizwin.dog_app.common.domain.model.Weight
import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse
import kotlinx.serialization.Serializable

@Serializable
data class DogDetailsResponseDTO(
    val breeds: List<BreedDTO> = emptyList(),
    val id: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
)

fun DogDetailsResponseDTO.toDogResponse(): DogDetailsResponse {
    return DogDetailsResponse(
        response = Dog(
            breeds = breeds.map { breed ->
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
            id = id,
            url = url,
            width = width,
            height = height
        )
    )
}
