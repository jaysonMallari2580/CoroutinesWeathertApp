package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class WeatherResponseListDTO (

    @SerializedName("list")
    val list: List<WeatherResponseDTO>,

    @SerializedName("city")
    val city: CityDTO?
    )