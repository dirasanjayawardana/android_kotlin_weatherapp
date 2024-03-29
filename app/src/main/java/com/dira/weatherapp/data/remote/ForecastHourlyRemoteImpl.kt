package com.dira.weatherapp.data.remote

import com.dira.weatherapp.data.WeatherService
import com.dira.weatherapp.data.model.ForecastHourlyResponseModel
import retrofit2.Response
import javax.inject.Inject


// @Inject -> digunakan untuk menandai konstruktor atau properti yang akan diinjeksi nilainya oleh Dagger (atau framework dependency injection lainnya) saat membuat instance dari kelas yang bersangkutan
class ForecastHourlyRemoteImpl @Inject constructor(val service: WeatherService) : ForecastHourlyRemote {
    // suspend -> Ketika fungsi suspend dipanggil, ia dapat menjalankan proses asinkron tanpa memblok thread secara langsung
    override suspend fun getForecastHourly(): Response<ForecastHourlyResponseModel> {
        return service.getForecastHourly()
    }

}