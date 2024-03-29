package com.dira.weatherapp.data.remote

import com.dira.weatherapp.data.model.ForecastHourlyResponseModel
import retrofit2.Response

interface ForecastHourlyRemote {

    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    suspend fun getForecastHourly(): Response<ForecastHourlyResponseModel>
}