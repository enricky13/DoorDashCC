package com.example.doordashcc.mainModule

import com.example.doordashcc.entity.RestaurantApiSingleton
import com.example.doordashcc.entity.RestaurantData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor : MainContract.Interactor {

    override fun fetchData(interactorOutPut: MainContract.InteractorOutPut) {
        RestaurantApiSingleton.retrofitService
            ?.getRestaurants(DOORDASH_LNG, DOORDASH_LAT, OFFSET, LIMIT)
            ?.enqueue(object : Callback<RestaurantData> {
                override fun onResponse(
                    call: Call<RestaurantData>,
                    response: Response<RestaurantData>
                ) {
                    response.body()?.let {
                        interactorOutPut.dataFetched(it)
                    }
                }

                override fun onFailure(call: Call<RestaurantData>, t: Throwable) {
                    interactorOutPut.dataFetchFailed(t.message ?: ERROR_NOT_FOUND)
                }
            })
    }

    companion object {
        const val DOORDASH_LNG = 37.422740
        const val DOORDASH_LAT = -122.139956
        const val OFFSET = 0
        const val LIMIT = 50
        const val ERROR_NOT_FOUND = "Error could not be found"
    }
}