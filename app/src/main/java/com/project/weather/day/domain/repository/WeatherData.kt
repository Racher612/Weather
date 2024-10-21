package com.project.weather.day.domain.repository


import com.project.weather.day.domain.Binary
import com.project.weather.day.domain.forecast.Forecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherData {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: Binary = Binary.NO,
        @Query("alerts") alerts: Binary = Binary.NO
    ): Response<Forecast>
}

