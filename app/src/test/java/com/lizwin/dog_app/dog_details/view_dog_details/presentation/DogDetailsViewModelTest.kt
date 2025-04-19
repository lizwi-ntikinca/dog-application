package com.lizwin.dog_app.dog_details.view_dog_details.presentation

import com.lizwin.dog_app.MainDispatcherRule
import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.domain.model.Breed
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.common.domain.model.Height
import com.lizwin.dog_app.common.domain.model.Weight
import com.lizwin.dog_app.dog_details.view_dog_details.domain.model.DogDetailsResponse
import com.lizwin.dog_app.dog_details.view_dog_details.domain.usecase.GetDogDetailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DogDetailsViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetDogDetailsUseCase
    private lateinit var viewModel: DogDetailsViewModel

    @Before
    fun setUp() {
        useCase = mock(GetDogDetailsUseCase::class.java)
        viewModel = DogDetailsViewModel(useCase)
    }

    @Test
    fun `onEvent Load should set isLoading to true`() = runTest {
        val expectedResponse = DogDetailsResponse(
            status = ResponseStatus.SUCCESS
        )

        `when`(useCase.invoke("dog_id")).thenReturn(expectedResponse)

        viewModel.onEvent(DogDetailsScreenUiEvent.Load("dog_id"))

        assertTrue(viewModel.uiState.value.isLoading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onEvent Load should update uiState with success`() = runTest {
        val expectedResponse = DogDetailsResponse(
            status = ResponseStatus.SUCCESS,
            response = Dog(
                breeds = listOf(
                    Breed(
                        weight = Weight(imperial = "50 -60", metric = "23 - 27"),
                        height = Height(imperial = "25 - 27", metric = "64 - 69"),
                        id = 12,
                        name = "Noname",
                        countryCode = "AG",
                        bredFor = "Coursing and hunting",
                        breedGroup = "Hound",
                        lifeSpan = "10 - 13 years",
                        temperament = "Aloof, Clownish, Dignified, Independent, Happy",
                        origin = "Afghanistan, Iran, Pakistan",
                        referenceImageId = ""
                    )
                ),
                id = "1",
                url = "dog.jpg",
                width = 100,
                height = 100
            )
        )

        `when`(useCase.invoke("dog_id")).thenReturn(expectedResponse)

        viewModel.onEvent(DogDetailsScreenUiEvent.Load("dog_id"))
        advanceUntilIdle()

        assertFalse(viewModel.uiState.value.isLoading)
        assertTrue(viewModel.uiState.value.isSuccess)
        assertTrue(viewModel.uiState.value.dogInformation.status == ResponseStatus.SUCCESS)
        assertEquals(expectedResponse, viewModel.uiState.value.dogInformation)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onEvent Load should update viewModel state with error when failed`() = runTest {
        `when`(useCase("wrong_id")).thenReturn(DogDetailsResponse(status = ResponseStatus.FAILED))

        viewModel.onEvent(DogDetailsScreenUiEvent.Load("wrong_id"))
        advanceTimeBy(3000L)

        assertFalse(viewModel.uiState.value.isLoading)
        assertFalse(viewModel.uiState.value.isSuccess)
        assertTrue(viewModel.uiState.value.dogInformation.status == ResponseStatus.FAILED)
        assertTrue(viewModel.uiState.value.dogInformation.response.breeds.isEmpty())
        assertTrue(viewModel.uiState.value.dogInformation.response.url.isEmpty())
        assertTrue(viewModel.uiState.value.dogInformation.response.id.isEmpty())
        assertTrue(viewModel.uiState.value.dogInformation.response.id.isEmpty())
    }
}