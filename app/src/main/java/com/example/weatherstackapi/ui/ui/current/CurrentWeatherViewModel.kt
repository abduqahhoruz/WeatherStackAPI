package com.example.weatherstackapi.ui.ui.current

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enru_translator.utils.Resource
import com.example.weatherstackapi.ui.data.net.api.RetrofitBuilder
import com.example.weatherstackapi.ui.data.net.model.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrentWeatherViewModel : ViewModel() {
    private var job: Job? = null
    private val mainCurrentWeatherResponse
            by lazy(LazyThreadSafetyMode.NONE) {
                MutableLiveData<Resource<CurrentWeatherResponse>>()
            }
    private val apiService = RetrofitBuilder.invoke()

    fun fetchCurrentWeather(location: String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            mainCurrentWeatherResponse.postValue(Resource.loading(null))
            try {
                val weatherFromApi = apiService.getCurrentWeather(location)
                mainCurrentWeatherResponse.postValue(Resource.success(weatherFromApi))
            } catch (e: Exception) {
                mainCurrentWeatherResponse.postValue(Resource.error("Something went Wrong",
                    null))
            }
        }
    }


    fun getCurrentWeather(): MutableLiveData<Resource<CurrentWeatherResponse>> {
        return mainCurrentWeatherResponse
    }

}

