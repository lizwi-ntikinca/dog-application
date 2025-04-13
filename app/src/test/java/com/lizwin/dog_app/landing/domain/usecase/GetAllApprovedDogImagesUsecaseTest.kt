package com.lizwin.dog_app.landing.domain.usecase

import com.lizwin.dog_app.MainDispatcherRule
import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.landing.domain.model.Dog
import com.lizwin.dog_app.landing.domain.model.DogListResponseData
import com.lizwin.dog_app.landing.domain.repository.DogListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetAllApprovedDogImagesUsecaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: DogListRepository
    private lateinit var useCase: GetAllApprovedDogImagesUsecase

    @Before
    fun setUp() {
        repository = mock(DogListRepository::class.java)
        useCase = GetAllApprovedDogImagesUsecase(repository)
    }

    @Test
    fun `invoke returns DogListResponseData from repository`() = runTest {
        val expectedResponse = DogListResponseData(
            status = ResponseStatus.SUCCESS,
            response = listOf(
                Dog(breeds = emptyList(), id = "1", url = "dog.jpg", width = 200, height = 300)
            )
        )

        `when`(repository.getAllApprovedDogImages()).thenReturn(expectedResponse)

        val result = useCase.invoke()

        assertEquals(expectedResponse, result)
    }
}
