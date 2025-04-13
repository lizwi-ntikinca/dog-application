package com.lizwin.dog_app.common.data.remote

import com.lizwin.dog_app.landing.data.model.DogResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface TheDogApi {
    @GET("images/search")
    suspend fun searchAllApprovedImages(
        @Query("order") order : String = "ASC",
        @Query("limit") limit : Int = 36,
        @Query("page") page : Int = 0
    ) : List<DogResponseDTO>
}