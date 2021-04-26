package com.example.weatherapp.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Forecast.DailyDTO
import com.example.weatherapp.databinding.DailyWeatherItemBinding
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

class DailyForecastAdapter(val mDailyList:List<DailyDTO>) : RecyclerView.Adapter<DailyForecastAdapter.DailyForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {
        return DailyForecastViewHolder(DailyWeatherItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return mDailyList.size
    }

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {
        val dailyForecast = mDailyList[position]
        holder.bindData(dailyForecast)
    }

    override fun onViewRecycled(holder: DailyForecastViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.binding.iconDailyIv.context).clear(holder.binding.iconDailyIv)
    }

    class DailyForecastViewHolder(val binding:DailyWeatherItemBinding) :RecyclerView.ViewHolder(binding.root){

        fun bindData(daily : DailyDTO){
            //dayName
            val date = Date(daily.time *1000)
            val calendar = Calendar.getInstance()
            calendar.time = date

            when(calendar.get(Calendar.DAY_OF_WEEK)){
                Calendar.SUNDAY -> binding.dayNameTv.text = "Sunday"
                Calendar.MONDAY -> binding.dayNameTv.text = "Monday"
                Calendar.TUESDAY -> binding.dayNameTv.text = "Tuesday"
                Calendar.WEDNESDAY -> binding.dayNameTv.text = "Wednesday"
                Calendar.THURSDAY -> binding.dayNameTv.text = "Thursday"
                Calendar.FRIDAY -> binding.dayNameTv.text = "Friday"
                Calendar.SATURDAY-> binding.dayNameTv.text = "Saturday"
            }


            //temp
            val maxTemp = calculateFahrenheit(daily.temp.maxTempDaily)
            binding.dailyMaxTv.text = maxTemp.toInt().toString()

            val  minTemp  = calculateFahrenheit(daily.temp.minTempDaily)
            binding.dailyMinTv.text = minTemp.toInt().toString()

            //icon
            var iconCode = daily.weatherList[0].icon
            val url = "https://openweathermap.org/img/w/$iconCode.png";
            Glide.with(binding.iconDailyIv.context)
                .load(url)
                .placeholder(R.drawable.ic_baseline_cloud_queue_24)
                .into(binding.iconDailyIv)


        }

        private fun calculateFahrenheit(degrees: Double): Double {
            val degreesInFahrenheit = ((degrees *9/5)-459.67)-32
            return degreesInFahrenheit
        }

    }
}