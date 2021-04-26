package com.example.weatherapp.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.models.Forecast.ForecastResponseListDTO
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import com.example.weatherapp.data.remote.network.WeatherManager
import io.reactivex.Single

class WeatherRepo {

    suspend fun getWeather(cityName:String): WeatherResponseListDTO{
        return WeatherManager().getWeather(cityName)
    }

     suspend fun getForecast(lat:Double ,lon:Double ): ForecastResponseListDTO{
        return WeatherManager().getForecast(lat,lon)
    }
}