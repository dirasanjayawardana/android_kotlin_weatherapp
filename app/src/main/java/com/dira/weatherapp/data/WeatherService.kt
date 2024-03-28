package com.dira.weatherapp.data

import com.fazztrack.bcasyariah.data.model.CurrentWeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    @GET("/data/2.5/weather?lat=-6.199929662592764&lon=106.85532153810563&appid=7a4da524900d52b79f04e1e42cd05d39&units=metric")
    suspend fun getCurrentWeather(): Response<CurrentWeatherResponseModel>

}