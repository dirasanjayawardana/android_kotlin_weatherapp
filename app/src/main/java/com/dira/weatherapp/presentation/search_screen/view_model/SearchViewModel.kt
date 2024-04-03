package com.dira.weatherapp.presentation.search_screen.view_model

// tempat menyalurkan data ke view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dira.weatherapp.data.model.search_weather.SearchWeatherResponseModel
import com.dira.weatherapp.data.remote.SearchWeatherRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// @HiltViewModel: Ini adalah anotasi yang menandakan bahwa kelas HomeViewModel adalah ViewModel yang akan diinjeksikan oleh Hilt. Ketika Anda menggunakan @HiltViewModel, Anda tidak perlu lagi menyediakan ViewModelProvider.Factory karena Hilt secara otomatis akan menangani pembuatan instance ViewModel dan menyediakan dependencies yang diperlukan ke dalam konstruktor ViewModel.

// @Inject: Ini adalah anotasi yang menandai konstruktor kelas HomeViewModel. Hilt akan memastikan bahwa instance dari CurrentWeatherRemote akan disediakan dan disuntikkan ke dalam konstruktor HomeViewModel saat membuat instance ViewModel.

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchWeatherRemote: SearchWeatherRemote): ViewModel() {

    // setter
    private val _serachWeather = MutableLiveData<SearchWeatherResponseModel>()
    private val _serachWeatherError = MutableLiveData<String>()
    // getter
    val serachWeather: LiveData<SearchWeatherResponseModel> get() = _serachWeather
    val serachWeatherError: LiveData<String> get() = _serachWeatherError

    fun getSearchWeater(city: String) = viewModelScope.launch(Dispatchers.IO) {
        searchWeatherRemote.getSearchWeather(city).let {
            if (it.isSuccessful) {
                _serachWeather.postValue(it.body())
            } else {
                _serachWeatherError.postValue(it.message())
            }

        }
    }
}