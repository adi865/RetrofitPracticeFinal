package com.example.retrofitpracticefinal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface RestaurantDao {

//     @Insert
//     suspend fun addRestaurant(restaurant: Restaurant)
    // OR

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRestaurants(restaurant: List<Restaurant>)

    @Query("DELETE FROM restaurantTable")
    suspend fun deleteAllRestaurants()

    @Query("SELECT * FROM restaurantTable")
    fun getRestaurants(): Flow<List<Restaurant>>
}