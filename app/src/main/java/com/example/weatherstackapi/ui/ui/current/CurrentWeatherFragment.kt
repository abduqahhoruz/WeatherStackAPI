package com.example.weatherstackapi.ui.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.enru_translator.utils.Status
import com.example.weatherstackapi.R
import com.example.weatherstackapi.ui.data.net.model.CurrentWeatherResponse
import kotlinx.android.synthetic.main.fragment_current_weather.*

class CurrentWeatherFragment : Fragment() {
    private var insPreference: CurrentWeatherResponse? = null
    private lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_current_weather, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        setUpObserver()
        searchBtn.setOnClickListener{
            searchWeather()
        }

    }

    private fun setUpObserver() {
        currentWeatherViewModel.getCurrentWeather().observe(viewLifecycleOwner,{ it ->
            when(it.status){
                Status.SUCCESS -> {
                    it.data.let {
                      tV_currentFragment.text = it.toString()
                        insPreference = it
                    }
                }
                Status.LOADING-> {

                }
                Status.ERROR->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
            et_Search.text?.clear()
        })
    }

    private fun setUpViewModel() {
        currentWeatherViewModel =
            ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
    }
    private fun searchWeather() {
       currentWeatherViewModel.fetchCurrentWeather(et_Search.text.toString())
    }

    override fun onPause() {
        super.onPause()


    }
}



