package com.lizwin.dog_app.dog_details.view_dog_details.data.repository

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.data.remote.TheDogApi
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.dog_details.view_dog_details.data.model.DogDetailsResponseDTO
import com.lizwin.dog_app.dog_details.view_dog_details.domain.repository.ViewDogDetailsRepository
import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class ViewDogDetailsRepositoryImplTest {

    private lateinit var api: TheDogApi
    private lateinit var repository: ViewDogDetailsRepository

    @Before
    fun setUp() {
        api = mock(TheDogApi::class.java)
        repository = ViewDogDetailsRepositoryImpl(api)
    }

    @Test
    fun `viewDogDetails returns success when API call succeeds`() = runBlocking {
        val dogDDetailsResponse = DogDetailsResponseDTO(
            breeds = emptyList(),
            id = "1",
            url = "dog.jpg",
            width = 100,
            height = 100
        )

        val expectedResponse = DogDetailsResponse(
            status = ResponseStatus.SUCCESS,
            response = Dog(
                breeds = emptyList(),
                id = "1",
                url = "dog.jpg",
                width = 100,
                height = 100
            )
        )

        `when`(api.getDogDetails("dog_id")).thenReturn(dogDDetailsResponse)

        val result = repository.viewDogDetails("dog_id")

        assertEquals(expectedResponse.status, result.status)
        assertEquals(expectedResponse.response, result.response)
    }

    @Test
    fun `viewDogDetails returns failed response when API call throws`() = runBlocking {
        `when`(api.getDogDetails("dog_id")).thenThrow(RuntimeException("API error"))

        val result = repository.viewDogDetails("dog_id")

        assertEquals(ResponseStatus.FAILED, result.status)
        assertTrue(result.response.id.isEmpty())
        assertTrue(result.response.url.isEmpty())
        assertTrue(result.response.breeds.isEmpty())
        assertEquals(0, result.response.height)
        assertEquals(0, result.response.width)
    }
}