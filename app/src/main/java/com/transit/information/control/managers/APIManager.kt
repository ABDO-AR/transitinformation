package com.transit.information.control.managers

import com.transit.information.control.repositories.MainRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIManager {

    // Fields:
    @Suppress("MemberVisibilityCanBePrivate")
    const val BASE_URL: String = "https://api.nationaltransport.ie"

    // Method(CreateClient):
    private fun createClient(): OkHttpClient = OkHttpClient().newBuilder().readTimeout(3, TimeUnit.MINUTES).connectTimeout(3, TimeUnit.MINUTES).build()

    // Method(CreateAPI):
    fun createAPI(): MainRepo = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(createClient()).build().create(MainRepo::class.java)
}