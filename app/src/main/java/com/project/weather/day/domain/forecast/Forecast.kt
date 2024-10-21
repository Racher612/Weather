package com.project.weather.day.domain.forecast

data class Forecast(
    val current: Current,
    val forecast: ForecastX,
    val location: Location
)