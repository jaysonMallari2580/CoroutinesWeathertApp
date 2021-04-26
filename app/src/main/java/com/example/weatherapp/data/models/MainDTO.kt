package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class MainDTO(
    @SerializedName("temp")
    val temp: String?,
    @SerializedName("feels_like")
    val feels_like: String?,
    @SerializedName("temp_min")
    val temp_min: String?,
    @SerializedName("temp_max")
    val temp_max: String?,
    @SerializedName("pressure")
    val pressure: String?,
    @SerializedName("sea_level")
    val sea_level: String?,
    @SerializedName("grnd_level")
    val grnd_level: String?,
    @SerializedName("humidity")
    val humidity: String?,
    @SerializedName("temp_kf")
    val temp_kf: String?
)