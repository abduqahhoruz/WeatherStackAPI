package com.example.weatherstackapi.ui.data.local.entity


import androidx.room.Entity

@Entity
data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String,
)