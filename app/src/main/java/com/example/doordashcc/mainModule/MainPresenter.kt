package com.example.doordashcc.mainModule

import com.example.doordashcc.entity.RestaurantData

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter {
    private var interactor: MainInteractor? = MainInteractor()

    override fun onActivityCreated() {
        interactor?.fetchData(object : MainContract.InteractorOutPut {
            override fun dataFetched(restaurantList: RestaurantData) {
                view?.hideProgressBar()
                view?.setReyclerWithInfo(restaurantList.restaurantList)
            }

            override fun dataFetchFailed(error: String) {
                view?.hideProgressBar()
                view?.displayErrorMessage(error)
            }
        })
    }

    override fun onDestroy() {
        interactor = null
    }
}