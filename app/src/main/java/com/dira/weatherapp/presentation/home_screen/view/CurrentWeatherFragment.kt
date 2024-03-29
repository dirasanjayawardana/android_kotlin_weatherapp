package com.dira.weatherapp.presentation.home_screen.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dira.weatherapp.R
import com.dira.weatherapp.data.model.ForecastDataHourly
import com.dira.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.dira.weatherapp.presentation.home_screen.adapter.ForecastHourlyAdapter
import com.dira.weatherapp.presentation.home_screen.view_model.CurrentWeatherViewModel
import com.dira.weatherapp.presentation.home_screen.view_model.ForecastHourlyViewModel
import com.dira.weatherapp.util.BaseFragment
import com.dira.weatherapp.util.HorizontalItemDecoration
import com.fazztrack.bcasyariah.data.model.CurrentWeatherResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : BaseFragment<FragmentCurrentWeatherBinding>() {

    private val viewModelCurrentWeather: CurrentWeatherViewModel by viewModels()
    private val viewModelForecastHourly: ForecastHourlyViewModel by viewModels()

    private lateinit var forecastHourlyAdapter: ForecastHourlyAdapter

    private val horizontalItemDecoration by lazy {
        HorizontalItemDecoration(
            resources.getDimensionPixelOffset(R.dimen.spacing16),
            true
        )
    }
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCurrentWeatherBinding {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        viewModelCurrentWeather.getCurrentWeather()
        viewModelForecastHourly.getForecastHourly()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModelCurrentWeather.currentWeather.observe(viewLifecycleOwner) {it ->
            setUpCurrentWeatherView(it)
        }

        viewModelForecastHourly.forecastHourly.observe(viewLifecycleOwner) {it ->
            setUpViewForecastHourly(it.forecastDataList.subList(0, 8)) // sublist untuk slice list berdasarkan index
        }
    }

    private fun setUpCurrentWeatherView(data: CurrentWeatherResponseModel) {
        binding.currentWeather.currentTemp.text = data.main.temp.toString()
        binding.currentWeather.currentCity.text = data.name.toString()
        binding.currentWeather.currentPressure.text = data.main.pressure.toString()
        binding.currentWeather.currentWind.text = data.wind.speed.toString()
        binding.currentWeather.currentHumidity.text = data.main.humidity.toString()
        binding.currentWeather.currentFeelsLike.text = data.main.feelsLike.toString()

        // set image dengan string resource template
        // val imageName = "weather${data.weather[0].icon}"
        // val resourceId = resources.getIdentifier(imageName, "drawable", "com.dira.weatherapp")
        // binding.currentWeather.currentImageWeather.setImageResource(resourceId)

        // load image dengan glide
        val imageCode = data.weather[0].icon
        Glide.with(this)
            .load("https://openweathermap.org/img/wn/$imageCode@2x.png?")
            .override(160, 80)
            .centerCrop()
            .into(binding.currentWeather.currentImageWeather)
    }

    private fun setUpViewForecastHourly(data: List<ForecastDataHourly>) {
        forecastHourlyAdapter = ForecastHourlyAdapter(data)
        binding.forecastHourly.recycleForecastHourly.adapter = forecastHourlyAdapter

        // untuk setting orientasi recycle viewnya, bisa juga secara manual edit orientation di component recycleView
        binding.forecastHourly.recycleForecastHourly.layoutManager = LinearLayoutManager(
            binding.root.context, LinearLayoutManager.HORIZONTAL, false
        )
        // menambahkan styling untuk setiap item recycleView
        binding.forecastHourly.recycleForecastHourly.apply {
            if (itemDecorationCount <= 0) {
                addItemDecoration(horizontalItemDecoration)
            }
        }
    }
}