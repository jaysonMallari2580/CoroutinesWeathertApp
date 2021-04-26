package com.example.weatherapp.data.models.Forecast

import com.google.gson.annotations.SerializedName

data class HourlyDTO(
    @SerializedName("dt")
    val time : Long,

    @SerializedName("temp")
    val temp : String,

    @SerializedName("weather")
    val weatherList: List<WeatherDTO>
)