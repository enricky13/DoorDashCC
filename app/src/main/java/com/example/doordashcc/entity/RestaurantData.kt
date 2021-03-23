package com.example.doordashcc.entity

import com.google.gson.annotations.SerializedName

data class RestaurantData(
    @SerializedName("stores")
    val restaurantList: List<Restaurant>
)

data class Restaurant(
    val id : Int,
    val name: String,
    val description: String,
    @SerializedName("cover_img_url")
    val restaurantImage: String,
    @SerializedName("next_open_time")
    val nextOpenTime: String,
    @SerializedName("next_close_time")
    val nextCloseTime: String,
    val status: Status
)

data class Status(
    @SerializedName("asap_available")
    val asapAvailable: Boolean,
    @SerializedName("asap_minutes_range")
    val asapMinutesRange: List<Int>?
)