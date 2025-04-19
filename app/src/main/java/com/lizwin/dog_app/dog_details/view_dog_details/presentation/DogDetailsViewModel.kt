package com.lizwin.dog_app.dog_details.view_dog_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizwin.dog_app.common.data.network.ResponseStatus
import com.lizwin.dog_app.dog_details.view_dog_details.domain.usecase.GetDogDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(DogDetailsScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: DogDetailsScreenUiEvent) {
        when(event) {
            is DogDetailsScreenUiEvent.Load -> {
                _uiState.value = _uiState.value.copy(isLoading = true)
                loadDetails(event.imgId)
            }
        }
    }

    private fun loadDetails(id: String) {
        viewModelScope.launch {
            val dogDetails = getDogDetailsUseCase(id)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                isSuccess = dogDetails.status == ResponseStatus.SUCCESS,
                dogInformation = dogDetails
            )
        }
    }
}