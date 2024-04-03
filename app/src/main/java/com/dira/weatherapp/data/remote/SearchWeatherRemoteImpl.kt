package com.dira.weatherapp.data.remote

import com.dira.weatherapp.data.WeatherService
import com.dira.weatherapp.data.model.search_weather.SearchWeatherResponseModel
import retrofit2.Response
import javax.inject.Inject

class SearchWeatherRemoteImpl @Inject constructor(val service: WeatherService) : SearchWeatherRemote {
    override suspend fun getSearchWeather(city: String): Response<SearchWeatherResponseModel> {
        return service.getSearchWeather(city,"7a4da524900d52b79f04e1e42cd05d39", "metric")
    }
}