package com.example.weatherapp.data.models.Forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponseListDTO (

    @SerializedName("hourly")
    val hourlyList : List<HourlyDTO>,

    @SerializedName("daily")
    val dailyList : List<DailyDTO>

)