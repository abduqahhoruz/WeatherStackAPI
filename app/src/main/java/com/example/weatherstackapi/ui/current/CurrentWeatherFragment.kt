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

class CurrentWeatherFragment : Fragment() {

    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        currentWeatherViewModel =
                ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_current_weather, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        currentWeatherViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}