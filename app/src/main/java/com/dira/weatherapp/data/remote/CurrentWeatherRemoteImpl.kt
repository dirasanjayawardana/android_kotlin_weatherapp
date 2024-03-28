package com.dira.weatherapp.data.remote

import com.dira.weatherapp.data.WeatherService
import com.fazztrack.bcasyariah.data.model.CurrentWeatherResponseModel
import retrofit2.Response
import javax.inject.Inject

// @Inject -> digunakan untuk menandai konstruktor atau properti yang akan diinjeksi nilainya oleh Dagger (atau framework dependency injection lainnya) saat membuat instance dari kelas yang bersangkutan
class CurrentWeatherRemoteImpl @Inject constructor(val service: WeatherService) : CurrentWeatherRemote {

    override suspend fun getCurrentWeather(): Response<CurrentWeatherResponseModel> {
        return service.getCurrentWeather()
    }
}