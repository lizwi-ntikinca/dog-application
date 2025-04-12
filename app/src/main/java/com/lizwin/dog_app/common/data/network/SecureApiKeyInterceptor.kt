package com.lizwin.dog_app.common.data.network

import android.content.Context
import com.lizwin.dog_app.common.data.security.SecureStorage
import okhttp3.Interceptor
import okhttp3.Response

class SecureApiKeyInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = SecureStorage.getApiKey(context) ?: ""

        val request = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey) // Some APIs use "Authorization"
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }
}