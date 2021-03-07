package com.example.weatherstackapi.ui.data.net.model

import com.example.weatherstackapi.ui.data.local.entity.Location
import com.example.weatherstackapi.ui.data.local.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeather: CurrentWeather,
    val location: Location,
    val request: Request
)