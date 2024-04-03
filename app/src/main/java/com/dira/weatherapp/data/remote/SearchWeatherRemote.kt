package com.dira.weatherapp.data.remote

import com.dira.weatherapp.data.model.forecast_hourly.ForecastHourlyResponseModel
import com.dira.weatherapp.data.model.search_weather.SearchWeatherResponseModel
import retrofit2.Response

interface SearchWeatherRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    suspend fun getSearchWeather(city: String): Response<SearchWeatherResponseModel>
}