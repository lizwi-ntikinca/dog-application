package com.lizwin.dog_app.common.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface TheDogApi {
    @GET("images/search")
    suspend fun searchAllApprovedImages(
        @Query("order") order : String = "ASC",
        @Query("limit") limit : Int = 26,
        @Query("page") page : Int = 0
    ) : String // To be implemented correctly
}