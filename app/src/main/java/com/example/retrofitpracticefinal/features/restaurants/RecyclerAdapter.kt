package com.example.retrofitpracticefinal.features.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitpracticefinal.data.Restaurant
import com.example.retrofitpracticefinal.databinding.RestaurantItemBinding

class RecyclerAdapter(): ListAdapter<Restaurant, RecyclerAdapter.RecyclerViewHolder>(RecyclerComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = RestaurantItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

//    override fun getItemCount(): Int {
//
//    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class RecyclerViewHolder(private val binding: RestaurantItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.apply {
                Glide.with(itemView)
                    .load(restaurant.logo)
                    .into(ivLogo)

                tvName.text = restaurant.name
                tvType.text = restaurant.type
                tvAddress.text = restaurant.address
            }
        }
    }
}

//can't be declared as an inner class
class RecyclerComparator : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem //compares the properties in the primary constructor
    }
}