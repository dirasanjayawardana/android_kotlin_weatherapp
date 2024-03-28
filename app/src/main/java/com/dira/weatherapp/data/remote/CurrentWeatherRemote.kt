package com.dira.weatherapp.data.remote

import com.fazztrack.bcasyariah.data.model.CurrentWeatherResponseModel
import retrofit2.Response

interface CurrentWeatherRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    suspend fun getCurrentWeather(): Response<CurrentWeatherResponseModel>
}