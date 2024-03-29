package com.dira.weatherapp.module

import com.dira.weatherapp.data.WeatherService
import com.dira.weatherapp.data.remote.ForecastHourlyRemote
import com.dira.weatherapp.data.remote.ForecastHourlyRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
// @Module digunakan untuk menandai kelas sebagai modul Dagger
@InstallIn(SingletonComponent::class)
// @InstallIn digunakan untuk menentukan komponen mana yang akan digunakan untuk menginstal modul tersebut
object ForecastHourlyModule {

    @Singleton
    // @Singleton digunakan untuk menandai bahwa instance yang diberikan oleh fungsi yang diberi anotasi ini akan bersifat singleton (hanya ada satu instance dari suatu kelas)
    @Provides
    // @Provides digunakan untuk menandai sebuah fungsi sebagai provider (fungsi yang bertanggung jawab untuk menyediakan instance dari suatu kelas tertentu)
    fun provideForecastHourlyRemoteDataSource(service: WeatherService) : ForecastHourlyRemote {
        return ForecastHourlyRemoteImpl(service)
    }
}