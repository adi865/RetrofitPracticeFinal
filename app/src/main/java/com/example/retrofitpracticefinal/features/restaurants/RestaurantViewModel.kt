package com.example.retrofitpracticefinal.features.restaurants

import androidx.lifecycle.*
import com.example.retrofitpracticefinal.api.RestaurantApi
import com.example.retrofitpracticefinal.data.Restaurant
import com.example.retrofitpracticefinal.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(repository: RestaurantRepository): ViewModel() {
//    private val restaurantsLiveData = MutableLiveData<List<Restaurant>>()
//    val restaurants: LiveData<List<Restaurant>> = restaurantsLiveData
//
//    init {
//        viewModelScope.launch {
//            val restaurants = api.getRestaurants()
//            delay(2000)
//            restaurantsLiveData.value = restaurants
//        }
//    }
    val restaurants = repository.getRestaurants().asLiveData()
}