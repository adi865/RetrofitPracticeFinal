package com.example.retrofitpracticefinal.data

import androidx.room.withTransaction
import com.example.retrofitpracticefinal.api.RestaurantApi
import com.example.retrofitpracticefinal.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: RestaurantApi,
    private val restaurantDatabase: RestaurantDatabase
) {
    private val restaurantDao = restaurantDatabase.getRestaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getRestaurants()
        },
        fetch = {
            delay(2000)
            api.getRestaurants()
        },
        savedFetchedData = { restaurants ->
            restaurantDatabase.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.addRestaurants(restaurants)
            }
        }
    )
}