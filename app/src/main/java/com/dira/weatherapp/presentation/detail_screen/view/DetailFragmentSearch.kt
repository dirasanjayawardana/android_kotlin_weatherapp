package com.dira.weatherapp.presentation.detail_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dira.weatherapp.data.model.search_weather.SearchWeatherResponseModel
import com.dira.weatherapp.databinding.FragmentDetailSearchBinding
import com.dira.weatherapp.util.BaseFragment

class DetailFragmentSearch(private val data: SearchWeatherResponseModel) : BaseFragment<FragmentDetailSearchBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailSearchBinding {
        return FragmentDetailSearchBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        binding.currentWeather.currentTemp.text = data.main.temp.toString()
        binding.currentWeather.currentCity.text = data.name.toString()
        binding.currentWeather.currentPressure.text = data.main.pressure.toString()
        binding.currentWeather.currentWind.text = data.wind.speed.toString()
        binding.currentWeather.currentHumidity.text = data.main.humidity.toString()
        binding.currentWeather.currentFeelsLike.text = data.main.feelsLike.toString()

        val imageCode = data.weather[0].icon
        Glide.with(this)
            .load("https://openweathermap.org/img/wn/$imageCode@2x.png?")
            .override(160, 80)
            .centerCrop()
            .into(binding.currentWeather.currentImageWeather)
    }
}