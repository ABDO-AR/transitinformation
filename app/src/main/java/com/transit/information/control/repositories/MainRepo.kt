package com.transit.information.control.repositories

import com.transit.information.model.Stop
import com.transit.information.model.Transit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface MainRepo {

    @GET("/api/production/interface")
    // Method(GetAPI):
    fun getAPI(): Call<Transit>
}