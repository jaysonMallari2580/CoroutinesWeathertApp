package com.example.weatherapp.data.models

import com.example.weatherapp.data.models.Forecast.WeatherDTO
import com.google.gson.annotations.SerializedName

data class WeatherResponseDTO(

    @SerializedName("main")
    val mainDTO: MainDTO,
    //val list : List<WeatherInfoDTO>

    /*@SerializedName("name")
    val cityNameDTO: String?,*/

    @SerializedName("weather")
    val weatherListDTO:List<WeatherDTO>,

    @SerializedName("dt")
    val time:Long


)