package com.dira.weatherapp.data.remote

import com.dira.weatherapp.data.WeatherService
import com.dira.weatherapp.data.model.current_weather.CurrentWeatherResponseModel
import retrofit2.Response
import javax.inject.Inject

// @Inject -> digunakan untuk menandai konstruktor atau properti yang akan diinjeksi nilainya oleh Dagger (atau framework dependency injection lainnya) saat membuat instance dari kelas yang bersangkutan
class CurrentWeatherRemoteImpl @Inject constructor(val service: WeatherService) : CurrentWeatherRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    override suspend fun getCurrentWeather(): Response<CurrentWeatherResponseModel> {
        return service.getCurrentWeather()
    }
}