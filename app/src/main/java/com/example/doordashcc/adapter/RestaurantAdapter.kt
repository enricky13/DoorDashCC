package com.example.doordashcc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doordashcc.enums.AvailabilityStatus
import com.example.doordashcc.R
import com.example.doordashcc.RestaurantViewHolder
import com.example.doordashcc.databinding.MainItemViewBinding
import com.example.doordashcc.entity.Restaurant
import com.example.doordashcc.entity.Status
import org.jetbrains.annotations.TestOnly

class RestaurantAdapter(
    private val restaurants: List<Restaurant>
) : RecyclerView.Adapter<RestaurantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = MainItemViewBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.main_item_view, parent, false)
        )
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        with(restaurants[position]) {
            holder.onBind(this, storeDeliveryStatus(status))
        }
    }

    override fun getItemCount() = restaurants.size

    private fun storeDeliveryStatus(status: Status): AvailabilityStatus {
        if (!status.asapAvailable) return AvailabilityStatus.CLOSED
        if (status.asapMinutesRange?.isNotEmpty() == true && status.asapMinutesRange[0] != status.asapMinutesRange[1]) return AvailabilityStatus.RANGE
        return AvailabilityStatus.FULL
    }

    @TestOnly
    fun storeDeliveryTest(status: Status): AvailabilityStatus = storeDeliveryStatus(status)
}