package com.example.doordashcc.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doordashcc.R
import com.example.doordashcc.adapter.RestaurantAdapter
import com.example.doordashcc.databinding.ActivityMainBinding
import com.example.doordashcc.entity.Restaurant


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainBinding: ActivityMainBinding
    private var presenter: MainPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        presenter = MainPresenter(this)
        presenter?.onActivityCreated()
    }

    override fun setReyclerWithInfo(restaurantList: List<Restaurant>) {
        mainBinding.recyclerView.adapter = RestaurantAdapter(restaurantList)
    }

    override fun displayErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        mainBinding.appLoadingPb.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }


}