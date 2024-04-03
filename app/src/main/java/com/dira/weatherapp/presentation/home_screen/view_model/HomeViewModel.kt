package com.dira.weatherapp.presentation.home_screen.view_model

// tempat menyalurkan data ke view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dira.weatherapp.data.remote.CurrentWeatherRemote
import com.dira.weatherapp.data.model.current_weather.CurrentWeatherResponseModel
import com.dira.weatherapp.data.model.forecast_hourly.ForecastHourlyResponseModel
import com.dira.weatherapp.data.remote.ForecastHourlyRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// @HiltViewModel: Ini adalah anotasi yang menandakan bahwa kelas HomeViewModel adalah ViewModel yang akan diinjeksikan oleh Hilt. Ketika Anda menggunakan @HiltViewModel, Anda tidak perlu lagi menyediakan ViewModelProvider.Factory karena Hilt secara otomatis akan menangani pembuatan instance ViewModel dan menyediakan dependencies yang diperlukan ke dalam konstruktor ViewModel.

// @Inject: Ini adalah anotasi yang menandai konstruktor kelas HomeViewModel. Hilt akan memastikan bahwa instance dari CurrentWeatherRemote akan disediakan dan disuntikkan ke dalam konstruktor HomeViewModel saat membuat instance ViewModel.

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentWeatherRemote: CurrentWeatherRemote,
    private val forecastHourlyRemote: ForecastHourlyRemote
) :
    ViewModel() {

    // setter
    private val _currentWeather = MutableLiveData<CurrentWeatherResponseModel>()
    private val _currentWeatherError = MutableLiveData<String>()
    // getter
    val currentWeather: LiveData<CurrentWeatherResponseModel> get() = _currentWeather
    val currentWeatherError: LiveData<String> get() = _currentWeatherError

    fun getCurrentWeather(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
        currentWeatherRemote.getCurrentWeather(lat, lon).let {
            if (it.isSuccessful) {
                _currentWeather.postValue(it.body())
            } else {
                _currentWeatherError.postValue(it.message())
            }

        }
    }

    // setter
    private val _forecastHourly = MutableLiveData<ForecastHourlyResponseModel>()
    private val _forecastHourlyError = MutableLiveData<String>()
    // getter
    val forecastHourly: LiveData<ForecastHourlyResponseModel> get() = _forecastHourly
    val forecastHourlyError: LiveData<String> get() = _forecastHourlyError

    fun getForecastHourly(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
        forecastHourlyRemote.getForecastHourly(lat, lon).let{
            if (it.isSuccessful) {
                _forecastHourly.postValue(it.body())
            } else {
                _forecastHourlyError.postValue(it.message())
            }
        }
    }
}