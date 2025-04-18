package com.lizwin.dog_app.dog_details.view_dog_details.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizwin.dog_app.dog_details.view_dog_details.domain.usecase.GetDogDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val getDogDetailsUseCase: GetDogDetailsUseCase
) : ViewModel() {
    fun loadDetails(id: String) {
        viewModelScope.launch {
            val response = getDogDetailsUseCase(id)
            Log.d("DOG_DETAILS", response.toString())
        }
    }
}