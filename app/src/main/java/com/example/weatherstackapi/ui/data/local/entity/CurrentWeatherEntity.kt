package com.example.weatherstackapi.ui.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
const val CURRENT_WEATHER_ID = 0
    @Entity(tableName = "current_weather")
    data class CurrentWeatherEntity(
        val cloudcover: Double,
        val feelslike: Double,
        val humidity: Double,
        @SerializedName("is_day")
        val isDay: String,
        @SerializedName("observation_time")
        val observationTime: String,
        val precip: Double,
        val pressure: Double,
        val temperature: Double,
        @SerializedName("uv_index")
        val uvIndex: Double,
        val visibility: Double,
        @SerializedName("weather_code")
        val weatherCode: Double,
        @SerializedName("weather_descriptions")
        val weatherDescriptions: List<String>,
        @SerializedName("weather_icons")
        val weatherIcons: List<String>,
        @SerializedName("wind_degree")
        val windDegree: Double,
        @SerializedName("wind_dir")
        val windDir: String,
        @SerializedName("wind_speed")
        val windSpeed: Double
    )
    {
        @PrimaryKey(autoGenerate = false)
        var id : Int = CURRENT_WEATHER_ID
    }
class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}
