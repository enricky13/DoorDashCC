package com.example.doordashcc.mainModule

import com.example.doordashcc.entity.Restaurant
import com.example.doordashcc.entity.RestaurantData

interface MainContract {
    interface View {
        fun setReyclerWithInfo(restaurantList: List<Restaurant>)
        fun displayErrorMessage(error: String)
        fun hideProgressBar()
    }

    interface Interactor {
        fun fetchData(interactorOutPut: InteractorOutPut)
    }

    interface InteractorOutPut {
        fun dataFetched(restaurantList: RestaurantData)
        fun dataFetchFailed(error: String)
    }

    interface Presenter {
        fun onActivityCreated()
        fun onDestroy()
    }
}