package com.example.myapplication.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayApi {
    @GET("planetary/apod")
    fun getPictureOTheDay(@Query("api_key")apiKey: String): Call<PODServerResponseData>
}