package com.myapplication.remote

import com.myapplication.ImagesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiController {

    @GET("api/?key=49804947-5c9bda4364e0e0e7c7e852a57&q=animals&per_page=20")
    suspend fun getImages(@Query("page") page: Int): ImagesModel
}