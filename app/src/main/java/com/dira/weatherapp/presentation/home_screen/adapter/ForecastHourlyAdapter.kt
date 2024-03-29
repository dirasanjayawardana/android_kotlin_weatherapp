package com.dira.weatherapp.presentation.home_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dira.weatherapp.data.model.ForecastDataHourly
import com.dira.weatherapp.data.model.ForecastHourlyResponseModel
import com.dira.weatherapp.databinding.ItemForecastHourlyBinding

class ForecastHourlyAdapter(private val data: List<ForecastDataHourly>):
    RecyclerView.Adapter<ForecastHourlyAdapter.ForecastHourlyViewHolder>() {

    inner class ForecastHourlyViewHolder(val binding: ItemForecastHourlyBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ForecastDataHourly) {
            binding.forecastTime.text = data.dt.toString()
            binding.forecastTemp.text = data.main.temp.toString()
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