package com.project.weather.day.data

import android.util.Log
import com.project.weather.day.domain.Binary
import com.project.weather.day.domain.forecast.Forecast
import com.project.weather.day.domain.repository.WeatherData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper(
    private var key : String = "",
    private var city : String = "",
    private var days : Int = 1,
    private var aqi : Binary = Binary.NO,
    private var alerts : Binary = Binary.NO,
){
    private var baseUrl = "https://api.weatherapi.com/v1/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getForecast() : Response<Forecast> {
        val apiManager = getInstance().create(WeatherData::class.java)
            var result = apiManager.getForecast(
                apiKey = key,
                city = city,
                days = days,
                aqi = aqi,
                alerts = alerts
            )
//            if (result.body()?.location.)
            return result
        }
    }