package com.lizwin.dog_app.common.di

import android.app.Application
import android.content.Context
import com.lizwin.dog_app.common.data.network.SecureApiKeyInterceptor
import com.lizwin.dog_app.common.data.remote.TheDogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val BASE_URL = "https://api.thedogapi.com/v1/"

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(SecureApiKeyInterceptor(context))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesApplicationContext(application: Application) : Context {
        return application.applicationContext
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): TheDogApi {
        return retrofit.create(TheDogApi::class.java)
    }
}