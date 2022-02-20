package com.transit.information.control.repositories

import com.transit.information.model.Transit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainRepo {

    // Required(Headers):
    @Headers("x-api-key: 772c591c13bf477fafc2c760283ed32a")
    @GET("/gtfsr/v1?format=json")
    // Method(GetAPI):
    fun getAPI(): Call<Transit>
}