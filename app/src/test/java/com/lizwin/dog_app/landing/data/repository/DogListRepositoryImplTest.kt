package com.lizwin.dog_app.landing.data.repository

import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.data.remote.TheDogApi
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.landing.data.model.DogResponseDTO
import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DogListRepositoryImplTest {

    private lateinit var api: TheDogApi
    private lateinit var repository: DogListRepositoryImpl

    @Before
    fun setUp() {
        api = mock(TheDogApi::class.java)
        repository = DogListRepositoryImpl(api)
    }

    @Test
    fun `getAllApprovedDogImages returns success when API call succeeds`() = runBlocking {
        val dogDtoList = listOf(
            DogResponseDTO(
                breeds = emptyList(),
                id = "1",
                url = "dog.jpg",
                width = 100,
                height = 100
            )
        )

        val expectedResponse = DogListResponseData(
            status = ResponseStatus.SUCCESS,
            response = listOf(
                Dog(
                    breeds = emptyList(),
                    id = "1",
                    url = "dog.jpg",
                    width = 100,
                    height = 100
                )
            )
        )

        // Return the list of DogResponseDTO from the mocked API
        `when`(api.searchAllApprovedImages()).thenReturn(dogDtoList)

        val result = repository.getAllApprovedDogImages()

        assertEquals(expectedResponse.status, result.status)
        assertEquals(expectedResponse.response, result.response)
    }

    @Test
    fun `getAllApprovedDogImages returns failed response when API call throws`() = runBlocking {
        `when`(api.searchAllApprovedImages()).thenThrow(RuntimeException("API error"))

        val result = repository.getAllApprovedDogImages()

        assertEquals(ResponseStatus.FAILED, result.status)
        assertTrue(result.response.isEmpty())
    }
}
