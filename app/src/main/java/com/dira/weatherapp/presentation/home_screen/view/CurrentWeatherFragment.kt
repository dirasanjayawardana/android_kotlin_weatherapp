package com.dira.weatherapp.presentation.home_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dira.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.dira.weatherapp.presentation.home_screen.view_model.CurrentWeatherViewModel
import com.dira.weatherapp.util.BaseFragment
import com.fazztrack.bcasyariah.data.model.CurrentWeatherResponseModel
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
            binding.current.currentTemp.text = it.main.temp.toString()
        }
    }
}