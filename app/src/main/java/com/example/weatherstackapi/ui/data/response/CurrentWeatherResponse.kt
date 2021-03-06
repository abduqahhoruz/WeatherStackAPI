package com.example.bismillah.data

import com.example.weatherstackapi.ui.data.response.CurrentWeather
import com.example.weatherstackapi.ui.data.db.entity.Location
import com.example.weatherstackapi.ui.data.db.entity.Request
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeather: CurrentWeather,
    val location: Location,
    val request: Request
)