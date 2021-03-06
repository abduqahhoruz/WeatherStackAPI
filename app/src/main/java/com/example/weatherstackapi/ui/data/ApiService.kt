package com.example.weatherstackapi.ui.data

import com.example.bismillah.data.CurrentWeatherResponse
import com.example.weatherstackapi.ui.common.BASE_URL
import com.example.weatherstackapi.ui.common.KEY_API
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.weatherstack.com/
// current?
// access_key=5f3ad969643559e6ccb3bfc8107fdbdd&
// query=New%20York

interface ApiService {
    @GET("current")
    suspend fun getCurrentWeather(
        @Query("query") query: String = "Fergana",
        @Query("access_key") key: String = KEY_API,
    ): CurrentWeatherResponse

    companion object {
        operator fun invoke(): ApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", KEY_API)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(ApiService::class.java)

        }
    }
}