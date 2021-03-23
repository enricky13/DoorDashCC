package com.example.doordashcc

import android.view.LayoutInflater
import com.example.doordashcc.adapter.RestaurantAdapter
import com.example.doordashcc.entity.Restaurant
import com.example.doordashcc.entity.Status
import com.example.doordashcc.enums.AvailabilityStatus
import com.example.doordashcc.mainModule.MainContract
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*
import org.mockito.internal.matchers.Equals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AppUnitTests {
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var itemsAdapter: RestaurantAdapter
    private val mockList = listOf(
        Restaurant(
            id = 1234,
            name = "Rest 1",
            description = "Rest 1 description",
            restaurantImage = "RestaurantImage mock link",
            nextOpenTime = "2021-03-20T03:00:00Z",
            nextCloseTime = "2021-03-21T00:21:57Z",
            status = Status(
                asapAvailable = false,
                asapMinutesRange = listOf(20, 20)
            )
        ),
        Restaurant(
            id = 5678,
            name = "Rest 2",
            description = "Rest 2 description",
            restaurantImage = "RestaurantImage mock link",
            nextOpenTime = "2021-05-21T03:00:00Z",
            nextCloseTime = "2021-06-21T00:21:57Z",
            status = Status(
                asapAvailable = true,
                asapMinutesRange = listOf(10, 20)
            )
        ),
        Restaurant(
            id = 9101112,
            name = "Rest 3",
            description = "Rest 3 description",
            restaurantImage = "RestaurantImage mock link",
            nextOpenTime = "2021-05-21T03:00:00Z",
            nextCloseTime = "2021-06-21T00:21:57Z",
            status = Status(
                asapAvailable = true,
                asapMinutesRange = listOf(20, 20)
            )
        )
    )

    @Before
    fun beforeTesting() {
        layoutInflater = mock(LayoutInflater::class.java)
        itemsAdapter = RestaurantAdapter(mockList)
    }

    @Test
    fun isAdapterItemCountWorking() {
        assertEquals(itemsAdapter.itemCount, 3)
    }

    @Test
    fun isStoreDeliveryTestReturningCorrectEnum(){
        assertTrue(Equals(itemsAdapter.storeDeliveryTest(mockList[0].status)).matches(AvailabilityStatus.CLOSED))
        assertTrue(Equals(itemsAdapter.storeDeliveryTest(mockList[1].status)).matches(AvailabilityStatus.RANGE))
        assertTrue(Equals(itemsAdapter.storeDeliveryTest(mockList[2].status)).matches(AvailabilityStatus.FULL))
    }
}