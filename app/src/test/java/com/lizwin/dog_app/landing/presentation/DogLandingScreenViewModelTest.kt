package com.lizwin.dog_app.landing.presentation

import com.lizwin.dog_app.MainDispatcherRule
import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.common.domain.model.Dog
import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import com.lizwin.dog_app.landing.domain.usecase.GetAllApprovedDogImagesUsecase
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

@ExperimentalCoroutinesApi
class DogLandingScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: GetAllApprovedDogImagesUsecase
    private lateinit var viewModel: DogLandingScreenViewModel

    @Before
    fun setUp() {
        useCase = mock(GetAllApprovedDogImagesUsecase::class.java)
        viewModel = DogLandingScreenViewModel(useCase)
    }

    @Test
    fun `onEvent FetchDogData should set isLoading to true`() = runTest {
        val expectedResponse = DogListResponseData(
            status = ResponseStatus.SUCCESS
        )

        `when`(useCase.invoke()).thenReturn(expectedResponse)

        viewModel.onEvent(LandingScreenUiEvent.FetchDogData)

        assertTrue(viewModel.state.value.isLoading)
    }

    @Test
    fun `FetchDogData should update viewModel state with success`() = runTest {
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

        `when`(useCase.invoke()).thenReturn(expectedResponse)

        viewModel.onEvent(LandingScreenUiEvent.FetchDogData)
        advanceUntilIdle()

        assertFalse(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.isSuccess)
        assertTrue(viewModel.state.value.status == ResponseStatus.SUCCESS)
        assertEquals(expectedResponse.response, viewModel.state.value.dogList)
    }

    @Test
    fun `FetchDogData should update viewModel state with error when failed`() = runTest {
        `when`(useCase()).thenReturn(DogListResponseData(status = ResponseStatus.FAILED))

        viewModel.onEvent(LandingScreenUiEvent.FetchDogData)
        advanceTimeBy(3000L)

        assertFalse(viewModel.state.value.isLoading)
        assertFalse(viewModel.state.value.isSuccess)
        assertTrue(viewModel.state.value.status == ResponseStatus.FAILED)
        assertTrue(viewModel.state.value.dogList.isEmpty())
        assertTrue(viewModel.state.value.filteredDogList.isEmpty())
    }
}
