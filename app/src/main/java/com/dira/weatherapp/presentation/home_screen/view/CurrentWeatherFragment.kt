package com.dira.weatherapp.presentation.home_screen.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dira.weatherapp.R
import com.dira.weatherapp.data.model.ForecastDataHourly
import com.dira.weatherapp.databinding.FragmentCurrentWeatherBinding
import com.dira.weatherapp.presentation.home_screen.adapter.ForecastHourlyAdapter
import com.dira.weatherapp.presentation.home_screen.view_model.CurrentWeatherViewModel
import com.dira.weatherapp.presentation.home_screen.view_model.ForecastHourlyViewModel
import com.dira.weatherapp.util.BaseFragment
import com.dira.weatherapp.util.HorizontalItemDecoration
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
            binding.currentWeather.currentTemp.text = it.main.temp.toString()
            binding.currentWeather.currentCity.text = it.name.toString()
            binding.currentWeather.currentPressure.text = it.main.pressure.toString()
            binding.currentWeather.currentWind.text = it.wind.speed.toString()
            binding.currentWeather.currentHumidity.text = it.main.humidity.toString()
            binding.currentWeather.currentFeelsLike.text = it.main.feelsLike.toString()
        }

        viewModelForecastHourly.forecastHourly.observe(viewLifecycleOwner) {it ->
            setUpViewForecastHourly(it.forecastDataList)
        }
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