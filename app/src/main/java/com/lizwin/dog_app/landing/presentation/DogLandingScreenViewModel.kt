package com.lizwin.dog_app.landing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.landing.domain.usecase.GetAllApprovedDogImagesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogLandingScreenViewModel @Inject constructor(
    private val getAllApprovedDogImagesUsecase: GetAllApprovedDogImagesUsecase
) : ViewModel() {

    private val _state = MutableStateFlow(LandingScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: LandingScreenUiEvent) {
        when(event) {
            is LandingScreenUiEvent.FetchDogData -> {
                _state.value = _state.value.copy(
                    isLoading = true
                )
                fetchLandingData()
            }
        }
    }

    private fun fetchLandingData() {
        viewModelScope.launch {
            val response = getAllApprovedDogImagesUsecase()

            _state.value = _state.value.copy(
                isLoading = false,
                isSuccess = response.status == ResponseStatus.SUCCESS,
                dogData = response
                //To do - add error message
            )
        }
    }
}