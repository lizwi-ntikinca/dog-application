package com.lizwin.dog_app.api_key.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import com.lizwin.dog_app.common.data.security.SecureStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiKeyViewModel @Inject constructor(private val context: Context) : ViewModel() {

    fun onEvent(event: ApiKeyEvent) {
        when(event) {
            is ApiKeyEvent.OnDoneButtonClicked -> {
                SecureStorage.saveApiKey(context = context, event.apiKey) // Store API key securely
            }
        }
    }
}