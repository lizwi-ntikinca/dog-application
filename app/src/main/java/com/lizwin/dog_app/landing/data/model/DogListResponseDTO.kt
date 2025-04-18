package com.lizwin.dog_app.landing.data.model

import com.lizwin.dog_app.common.data.model.BreedDTO
import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.common.domain.model.Breed
import com.lizwin.dog_app.common.domain.model.Weight
import com.lizwin.dog_app.common.domain.model.Height
import kotlinx.serialization.Serializable

@Serializable
data class DogResponseDTO(
    val breeds: List<BreedDTO>,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
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