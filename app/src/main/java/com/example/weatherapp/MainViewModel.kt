package com.example.weatherapp
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.models.WeatherResponseDTO
import com.example.weatherapp.data.models.WeatherResponseListDTO
import com.example.weatherapp.data.remote.network.RetrofitService
import com.example.weatherapp.data.repositories.WeatherRepo
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Dispatcher
import androidx.lifecycle.liveData
import com.example.weatherapp.Util.Resource
import com.example.weatherapp.Util.Status
import kotlinx.coroutines.Dispatchers


class MainViewModel : ViewModel() {

    //lat &lon
    private var _lat:Double = 0.00
    val lat get() = _lat

    private var _lon:Double = 0.00
    val lon get() = _lon

    private var _weatherList = MutableLiveData<List<WeatherResponseDTO>>()
    val weatherList get() = _weatherList

    private var _weatherResponse = MutableLiveData<WeatherResponseDTO>()
    val weatherResponse get() = _weatherResponse

    private var _cityName = MutableLiveData<String>()
    val cityName get() = _cityName

    private val disposable = CompositeDisposable()
    private val weatherRepo: WeatherRepo by lazy {
        WeatherRepo()
    }

    init {
    }

    private var _test = MutableLiveData<String>()
    val test get() = _test

    private var _mainInfoMinTemp = MutableLiveData<String>()
    val mainInfoMinTemp = _mainInfoMinTemp

    private var _mainInfoMaxTemp = MutableLiveData<String>()
    val mainInfoMaxTemp = _mainInfoMaxTemp

    private var _mainInfo = MutableLiveData<String>()
    val mainInfo get() = _mainInfo

    private var _cityNameInfo = MutableLiveData<String>()
    val cityNameInfo get() = _cityNameInfo

    private var _weatherHint = MutableLiveData<String>()
    val weatherHint get() = _weatherHint

    private var _icon = MutableLiveData<String>()
    val icon get() = _icon



    fun getWeather(cityName: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data=weatherRepo.getWeather(cityName)))
        }catch (e:Exception){
            emit(Resource.error(data = null,e.message?:"Error Occured"))
        }
    }



    fun onGetWeatherSuccess(weatherResponse: WeatherResponseListDTO) {

        _lat = weatherResponse.city?.coord?.lat!!
        _lon = weatherResponse.city?.coord?.lon!!

        println("_LAT = $lat and _LON = $lon")

        _weatherList.value = weatherResponse.list

        _test.value = weatherResponse.list[0].mainDTO.temp

        //temp
        _mainInfo.value = calculateFahrenheit(weatherResponse.list[0].mainDTO.temp!!.toDouble()).toInt().toString()+"\u2109"

        _mainInfoMaxTemp.value = calculateFahrenheit(weatherResponse.list[0].mainDTO.temp_max!!.toDouble()).toInt().toString()

        _mainInfoMinTemp.value = calculateFahrenheit(weatherResponse.list[0].mainDTO.temp_min!!.toDouble()).toInt().toString()
        //cityName
        _cityNameInfo.value = weatherResponse.city?.name

        //weather Hint
        _weatherHint.value = weatherResponse.list[0].weatherListDTO[0].description.toString()

        //weather Icon
        _icon.value = weatherResponse.list[0].weatherListDTO[0].icon.toString()
    }



    private fun onGetWeatherError(e: Throwable) {
        e.message.let { Log.d(TAG, it.toString())}
    }


    fun passMeTheCityName(cityName:String){
        getWeather(cityName)
    }

    companion object {
        private val TAG = MainViewModel::class.java.name
    }

    private fun calculateFahrenheit(degrees: Double): Double {
        val degreesInFahrenheit = (degrees * 1.8) + 32
        return degreesInFahrenheit
    }



}


