package com.example.weatherapp.data.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


    object RetrofitService {
        private const val TIMEOUT = 60 * 1000.toLong()
        private const val BASE_URL = "https://api.openweathermap.org/"

        private fun providesOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)

            val clientBuilder =
                OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(
                    TIMEOUT,
                    TimeUnit.SECONDS
                ).writeTimeout(TIMEOUT, TimeUnit.SECONDS)

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)

            return clientBuilder.build()
        }

        fun providesRetrofitService(): Retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(providesOkHttpClient())
                .build()

    }
