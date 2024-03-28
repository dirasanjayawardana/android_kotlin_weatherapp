package com.dira.weatherapp.presentation.home_screen.view_model

// tempat menyalurkan data ke view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dira.weatherapp.data.remote.CurrentWeatherRemote
import com.fazztrack.bcasyariah.data.model.CurrentWeatherResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val currentWeatherRemote: CurrentWeatherRemote) :
    ViewModel() {
    private val _currentWeather = MutableLiveData<CurrentWeatherResponseModel>()
    private val _currentWeatherError = MutableLiveData<String>()

    val homeNews: LiveData<CurrentWeatherResponseModel> get() = _currentWeather

    val homeNewsError: LiveData<String> get() = _currentWeatherError

    fun getCurrentWeather() = viewModelScope.launch(Dispatchers.IO) {
        currentWeatherRemote.getCurrentWeather().let {
            if (it.isSuccessful) {
                _currentWeather.postValue(it.body())
            } else {
                _currentWeatherError.postValue(it.message())
            }

        }
    }
}