package com.example.retrofitpracticefinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurantTable")
data class Restaurant(
    //primary key not declared, because names of the restaurants are unique
    @PrimaryKey
    val name: String,
    val type: String,
    val logo: String,
    val address: String
)