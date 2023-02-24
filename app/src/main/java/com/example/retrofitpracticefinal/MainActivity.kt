package com.example.retrofitpracticefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitpracticefinal.databinding.ActivityMainBinding
import com.example.retrofitpracticefinal.features.restaurants.RecyclerAdapter
import com.example.retrofitpracticefinal.features.restaurants.RestaurantViewModel
import com.example.retrofitpracticefinal.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val restaurantViewModel: RestaurantViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerAdapter = RecyclerAdapter()

        binding.apply {
            rvRestaurant.apply {
                adapter = recyclerAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            restaurantViewModel.restaurants.observe(this@MainActivity) { result ->
                 recyclerAdapter.submitList(result.data)


                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                tvError.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                tvError.text = result.error?.localizedMessage
            }
        }
    }
}