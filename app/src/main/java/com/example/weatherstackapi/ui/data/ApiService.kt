package com.example.weatherstackapi.ui.data

import com.example.weatherstackapi.ui.common.KEY_API
import com.example.weatherstackapi.ui.data.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val key = "2a0b4b7476b779539aa9e83d2ac53968"

interface ApiService {
    //http://api.weatherstack.com/current?access_key=2a0b4b7476b779539aa9e83d2ac53968&query=New%20York

    @GET("current")
    fun getCurrentWeatherAsync(
        @Query("query")
        location: String,
        @Query("language")
        language: String = "en"
    ): Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(): ApiService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", KEY_API)
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
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}