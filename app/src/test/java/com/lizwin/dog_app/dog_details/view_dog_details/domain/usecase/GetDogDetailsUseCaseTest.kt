package com.lizwin.dog_app.dog_details.view_dog_details.domain.usecase

import com.lizwin.dog_app.MainDispatcherRule
import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse
import com.lizwin.dog_app.dog_details.view_dog_details.domain.repository.ViewDogDetailsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetDogDetailsUseCaseTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: ViewDogDetailsRepository
    private lateinit var useCase: GetDogDetailsUseCase

    @Before
    fun setUp() {
        repository = mock(ViewDogDetailsRepository::class.java)
        useCase = GetDogDetailsUseCase(repository)
    }

    @Test
    fun `invoke returns DogDetailsResponse from repository`() = runTest {
        val expectedResponse = DogDetailsResponse(
            status = ResponseStatus.SUCCESS,
            response = Dog(
                breeds = emptyList(),
                id = "1",
                url = "dog.jpg",
                width = 200,
                height = 300
            )
        )

        `when`(repository.viewDogDetails("dog_id")).thenReturn(expectedResponse)

        val result = useCase.invoke("dog_id")

        assertEquals(expectedResponse, result)
    }
}