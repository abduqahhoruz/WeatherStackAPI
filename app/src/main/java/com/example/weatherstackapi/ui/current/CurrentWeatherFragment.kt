package com.example.weatherstackapi.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherstackapi.R
import com.example.weatherstackapi.ui.data.ApiService
import com.example.weatherstackapi.ui.data.response.CurrentWeatherResponse
import kotlinx.android.synthetic.main.fragment_current_weather.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
private lateinit var currentWeatherResponse: CurrentWeatherResponse
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentWeatherViewModel =
            ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_current_weather, container, false)
        val textView: TextView = root.findViewById(R.id.tV_currentFragment)
        currentWeatherViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiService = ApiService()
        GlobalScope.launch(Dispatchers.Main) {
            GlobalScope.launch(Dispatchers.IO) {
                currentWeatherResponse =
                    apiService.getCurrentWeatherAsync("London", "en").await()
            }
            tV_currentFragment.text = currentWeatherResponse.currentWeatherEntry.temperature.toString()

        }
    }
}