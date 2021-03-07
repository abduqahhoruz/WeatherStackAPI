package com.example.weatherstackapi.ui.ui.future.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherstackapi.R

class FutureListWeatherFragment : Fragment() {

    private lateinit var futureListWeatherViewModel: FutureListWeatherViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        futureListWeatherViewModel =
                ViewModelProvider(this).get(FutureListWeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_future_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        futureListWeatherViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}