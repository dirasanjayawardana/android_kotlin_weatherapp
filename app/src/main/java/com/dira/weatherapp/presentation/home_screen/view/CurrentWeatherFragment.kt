package com.dira.weatherapp.presentation.home_screen.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dira.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.dira.weatherapp.presentation.home_screen.view_model.CurrentWeatherViewModel
import com.dira.weatherapp.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : BaseFragment<FragmentCurrentWeatherBinding>() {

    private val viewModel: CurrentWeatherViewModel by viewModels()
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCurrentWeatherBinding {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        viewModel.getCurrentWeather()
        viewModel.currentWeather.observe(viewLifecycleOwner) {it ->
            binding.current.currentTemp.text = it.main.temp.toString()
            binding.current.currentCity.text = it.name.toString()
            binding.current.currentPressure.text = it.main.pressure.toString()
            binding.current.currentWind.text = it.wind.speed.toString()
            binding.current.currentHumidity.text = it.main.humidity.toString()
            binding.current.currentFeelsLike.text = it.main.feelsLike.toString()
            // Log.d("data weather", it.toString())
        }
    }
}