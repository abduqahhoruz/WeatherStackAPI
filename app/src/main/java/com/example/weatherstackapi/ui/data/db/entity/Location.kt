package com.example.weatherstackapi.ui.data.db.entity


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity
data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Double,
    val lon: String,
    val name: String,
    val region: String,
    @SerializedName("timezone_id")
    val timezoneId: String,
    @SerializedName("utc_offset")
    val utcOffset: String
)