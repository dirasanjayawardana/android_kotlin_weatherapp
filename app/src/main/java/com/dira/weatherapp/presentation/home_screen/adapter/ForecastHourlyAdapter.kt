package com.dira.weatherapp.presentation.home_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dira.weatherapp.data.model.forecast_hourly.ForecastDataHourly
import com.dira.weatherapp.databinding.ItemForecastHourlyBinding
import java.text.SimpleDateFormat
import java.util.Date

class ForecastHourlyAdapter(private val data: List<ForecastDataHourly>):
    RecyclerView.Adapter<ForecastHourlyAdapter.ForecastHourlyViewHolder>() {

    inner class ForecastHourlyViewHolder(val binding: ItemForecastHourlyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ForecastDataHourly) {
            val forecastTime = convertUnixTimeToDate(data.dt)
            val formattedTime = SimpleDateFormat("HH:mm").format(forecastTime) // format : dd/MM/yyyy HH:mm

            binding.forecastTime.text = formattedTime
            binding.forecastTemp.text = data.main.temp.toString()

            // load image dengan glide
            val imageCode = data.weather[0].icon
            Glide.with(binding.root.context)
                .load("https://openweathermap.org/img/wn/$imageCode@2x.png?")
                .override(160, 80)
                .centerCrop()
                .into(binding.forecastImage)
        }

        private fun convertUnixTimeToDate(unixTime: Long): Date {
            return Date(unixTime * 1000)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHourlyViewHolder {
        return ForecastHourlyViewHolder(
            ItemForecastHourlyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ForecastHourlyViewHolder, position: Int) {
        holder.bind(data[position])
    }
}