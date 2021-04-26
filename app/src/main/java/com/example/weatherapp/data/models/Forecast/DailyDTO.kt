package com.example.weatherapp.data.models.Forecast

import com.google.gson.annotations.SerializedName

data class DailyDTO (

    @SerializedName("dt")
    val time: Long,

    @SerializedName("temp")
    val temp: TempDailyDTO,

    @SerializedName("weather")
    val weatherList : List<WeatherDailyDTO>
)