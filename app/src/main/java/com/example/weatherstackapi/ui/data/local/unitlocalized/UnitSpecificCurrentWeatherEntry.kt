package com.example.weatherstackapi.ui.data.local.unitlocalized

interface UnitSpecificCurrentWeatherEntry {
    val cloudcover: Double
    val feelslike: Double
    val humidity: Double
    val isDay: String
    val observationTime: String
    val precip: Double
    val pressure: Double
    val temperature: Double
    val uvIndex: Double
    val visibility: Double
}