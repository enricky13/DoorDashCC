package com.example.doordashcc

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.doordashcc.databinding.MainItemViewBinding
import com.example.doordashcc.entity.Restaurant
import com.example.doordashcc.entity.Status
import com.example.doordashcc.enums.AvailabilityStatus

class RestaurantViewHolder(private val itemViewBinding: MainItemViewBinding) :
    RecyclerView.ViewHolder(itemViewBinding.root) {

    fun onBind(restaurant: Restaurant, deliveryStatus: AvailabilityStatus) {
        with(itemViewBinding) {
            restaurantNameTv.text = restaurant.name
            restaurantDeliveryTimeTv.text = getAvailabilityString(deliveryStatus, restaurant.status)
            Glide.with(itemView)
                .load(restaurant.restaurantImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageLoadingPb.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageLoadingPb.visibility = View.GONE
                        return false
                    }

                })
                .into(restaurantLogoTv)
        }
    }

    private fun getAvailabilityString(
        howAvailableIsIt: AvailabilityStatus,
        status: Status
    ): String {
        val res = itemView.resources
        return when (howAvailableIsIt) {
            AvailabilityStatus.FULL -> res.getString(
                R.string.store_asap_tv,
                status.asapMinutesRange?.get(0)
            )
            AvailabilityStatus.RANGE -> res.getString(
                R.string.store_asap_range_tv,
                status.asapMinutesRange?.get(0), status.asapMinutesRange?.get(1)
            )
            AvailabilityStatus.CLOSED -> res.getString(R.string.store_closed_tv)
        }
    }
}