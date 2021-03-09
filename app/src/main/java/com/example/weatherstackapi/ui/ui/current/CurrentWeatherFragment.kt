package com.example.weatherstackapi.ui.ui.current

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.enru_translator.utils.Status
import com.example.weatherstackapi.BuildConfig
import com.example.weatherstackapi.R
import kotlinx.android.synthetic.main.fragment_current_weather.*

class CurrentWeatherFragment : Fragment() {
    private val TAG = "TAG"
    private val currentWeatherViewModel by lazy { ViewModelProvider(this).get(CurrentWeatherViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }

    private fun fetchData() {
        currentWeatherViewModel.fetchCurrentWeather("Namangan")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObserver()
    }
    @SuppressLint("SetTextI18n")
    private fun setUpObserver() {
        Log.d(TAG, "setUpObserver: ")
        currentWeatherViewModel.getCurrentWeather().observe(viewLifecycleOwner, { it ->
            when (it.status) {
                Status.SUCCESS -> {
                    it.data.let {
                        progressBar_loading.visibility = View.GONE
                        textView_loading.visibility = View.GONE
                        if (it != null) {
                            tv_location.text = it.location.region
                            tv_observe_time.text = it.location.localtime
                            textView_condition.text =
                                it.currentWeather.weatherDescriptions[0]
                            textView_feels_like_temperature.text =
                                "Feels like " + it.currentWeather.feelslike.toString().plus("°C")
                            textView_temperature.text =
                                it.currentWeather.temperature.toString().plus("°C")
                            textView_wind.text =
                                "Wind: " + it.currentWeather.windDir + "," + it.currentWeather.windSpeed + " m/s"
                            textView_precipitation.text =
                                "Precipiation" + it.currentWeather.precip.toString().plus(" mm")
                            textView_visibility.text =
                                "Visibility: " + it.currentWeather.visibility + " km"
                            Glide.with(this@CurrentWeatherFragment)
                                .load(it.currentWeather.weatherIcons[0])
                                .into(imageView_condition_icon)

                        }

                    }
                }
                Status.LOADING -> {
                    Log.d(TAG, "setUpObserver: Loading")
                    progressBar_loading.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Log.d(TAG, "setUpObserver: Error")
                    progressBar_loading.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}




