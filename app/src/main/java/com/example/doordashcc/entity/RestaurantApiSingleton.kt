package com.example.doordashcc.entity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestaurantApiSingleton {
    private const val BASE_URL = "https://api.doordash.com/"

    private fun retrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofitService: RestaurantApi? = retrofit().create(RestaurantApi::class.java)
}