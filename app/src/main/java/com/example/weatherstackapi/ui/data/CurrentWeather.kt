package com.example.bismillah.data

import com.example.weatherstackapi.ui.data.Current


data class CurrentWeather(
    val current: Current,
    val location: Location,
    val request: Request
)