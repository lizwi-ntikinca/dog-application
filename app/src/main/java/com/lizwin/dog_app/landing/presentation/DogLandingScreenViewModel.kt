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

            is LandingScreenUiEvent.Search -> {
                _state.value = _state.value.copy(isLoading = true)
                onSearchQueryChanged(event.query)
            }
        }
    }

    private fun fetchLandingData() {
        viewModelScope.launch {
            val results = getAllApprovedDogImagesUsecase()

            _state.value = _state.value.copy(
                status = results.status,
                isLoading = false,
                isSuccess = results.status == ResponseStatus.SUCCESS,
                dogList = results.response,
                filteredDogList = results.response
                //To do - add error message
            )
        }
    }

    private fun onSearchQueryChanged(query: String) {
        val filtered = if (query.isBlank()) {
            _state.value.dogList
        } else _state.value.dogList.filter { dog ->
            dog.breeds.firstOrNull()?.name?.contains(query, ignoreCase = true) == true
        }

        _state.value = _state.value.copy(
            isLoading = false,
            filteredDogList = filtered
        )
    }
}