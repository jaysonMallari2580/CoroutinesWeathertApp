package com.example.weatherapp.data.remote.network

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.engine.Resource
import com.example.weatherapp.R
import com.example.weatherapp.data.models.Forecast.ForecastResponseListDTO
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {

    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofitService()

    companion object{
        private val include ="hourly,daily"
        private val appid = "600788429c6ebf4322a78f9bba4f58da"
        private val metric = "metric"
    }


    init {
        service = retrofit.create(WeatherService::class.java)
    }

    suspend fun getWeather(q:String) = service.getWeather(q)

     suspend fun getForecast(lat:Double, lon:Double) = service.getForecast(lat,lon)

    interface WeatherService {
        //api.openweathermap.org/data/2.5/weather?q=New%20Jersey&appid=600788429c6ebf4322a78f9bba4f58da
        @GET("data/2.5/forecast")
        suspend fun getWeather(@Query("q")q:String,
                       @Query("appid")appid:String=WeatherManager.appid ,
                       @Query("units")units:String=WeatherManager.metric ) :WeatherResponseListDTO

        @GET("data/2.5/onecall")
        suspend fun getForecast(@Query("lat")lat:Double,
        @Query("lon")lon:Double,
        @Query("include")include:String = WeatherManager.include,
        @Query("appid")appid:String=WeatherManager.appid) : ForecastResponseListDTO

    }
}