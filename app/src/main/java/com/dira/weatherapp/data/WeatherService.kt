package com.dira.weatherapp.data

import com.dira.weatherapp.data.model.forecast_hourly.ForecastHourlyResponseModel
import com.dira.weatherapp.data.model.current_weather.CurrentWeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<CurrentWeatherResponseModel>

    @GET("/data/2.5/forecast")
    suspend fun getForecastHourly(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<ForecastHourlyResponseModel>


}