package com.dira.weatherapp.presentation.detail_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dira.weatherapp.data.model.forecast_hourly.ForecastDataHourly
import com.dira.weatherapp.databinding.FragmentDetailBinding
import com.dira.weatherapp.util.BaseFragment
import java.text.SimpleDateFormat
import java.util.Date

class DetailFragment(private val forecastDataHourly: ForecastDataHourly) : BaseFragment<FragmentDetailBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        val forecastTime = Date(forecastDataHourly.dt * 1000)
        val formattedTime = SimpleDateFormat("dd/MM/yyyy - HH:mm").format(forecastTime) // format : dd/MM/yyyy HH:mm

        binding.timeForecast.text = formattedTime
        binding.currentWeather.currentTemp.text = forecastDataHourly.main.temp.toString()
        binding.currentWeather.currentPressure.text = forecastDataHourly.main.pressure.toString()
        binding.currentWeather.currentWind.text = forecastDataHourly.wind.speed.toString()
        binding.currentWeather.currentHumidity.text = forecastDataHourly.main.humidity.toString()
        binding.currentWeather.currentFeelsLike.text = forecastDataHourly.main.feelsLike.toString()
        binding.currentWeather.currentCity.text = forecastDataHourly.weather[0].description.toString()

        // load image dengan glide
        val imageCode = forecastDataHourly.weather[0].icon
        Glide.with(this)
            .load("https://openweathermap.org/img/wn/$imageCode@2x.png?")
            .override(160, 80)
            .centerCrop()
            .into(binding.currentWeather.currentImageWeather)
    }
}