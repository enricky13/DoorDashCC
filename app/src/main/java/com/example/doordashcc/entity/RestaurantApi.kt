package com.example.doordashcc.entity

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {
    @GET(RESTAURANTS)
    fun getRestaurants(
        @Query(LAT) latitude: Double,
        @Query(LNG) longitude: Double,
        @Query(OFFSET) offset: Int,
        @Query(LIMIT) limit: Int
    ): Call<RestaurantData>

    companion object{
        const val RESTAURANTS = "v1/store_feed/"
        const val LAT = "lat"
        const val LNG = "lng"
        const val OFFSET = "offset"
        const val LIMIT = "limit"
    }
}