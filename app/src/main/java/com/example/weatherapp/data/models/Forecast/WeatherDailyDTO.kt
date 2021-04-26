package com.example.weatherapp.data.models.Forecast

import com.google.gson.annotations.SerializedName

data class WeatherDailyDTO (

    @SerializedName("icon")
    val icon:String
)