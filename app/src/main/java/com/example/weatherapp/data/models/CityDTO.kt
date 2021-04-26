package com.example.weatherapp.data.models

import com.google.gson.annotations.SerializedName

data class CityDTO (

    @SerializedName("name")
    val name:String?,

    @SerializedName("coord")
    val coord: Coord,

        )