package com.example.bismillah.data

import com.example.weatherstackapi.ui.common.KEY_API
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherstack.com/
// current?
// access_key=5f3ad969643559e6ccb3bfc8107fdbdd&
// query=New%20York

interface ApiService {
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("access_key") key: String = KEY_API,
        @Query("query") query: String = "Fergana"
    ): CurrentWeather
}