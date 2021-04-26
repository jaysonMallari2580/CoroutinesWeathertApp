package com.example.weatherapp.data.models.Forecast

import com.google.gson.annotations.SerializedName

data class TempDailyDTO (

    @SerializedName("day")
    val dayTemp : Double,

    @SerializedName("min")
    val minTempDaily : Double,

    @SerializedName("max")
    val maxTempDaily : Double

)