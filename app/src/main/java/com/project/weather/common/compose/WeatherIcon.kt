package com.project.weather.common.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.weather.R

@Composable
fun WeatherIcon(
    weather : String,
    size : Int = 40
){
    val condition = if (weather[weather.length - 1] == ' ')
        weather.substring(0, weather.length - 1)
    else weather
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = when(condition){
                "Light snow" -> R.drawable.cloud_snow
                "Clear" -> R.drawable.sunny
                "Sunny" -> R.drawable.sunny
                "Moderate rain" -> R.drawable.cloud_rain
                "Partly cloudy" -> R.drawable.cloud_sun
                "Partly Cloudy" -> R.drawable.cloud_sun
                "Cloudy" -> R.drawable.cloud_sun
                "Moderate or heavy rain with thunder" -> R.drawable.rain_lighting
                "Mist" -> R.drawable.cloud_sun
                "Overcast" -> R.drawable.cloud_sun
                "Moderate or heavy snow showers" -> R.drawable.cloud_snow
                "Light drizzle" -> R.drawable.cloud_rain
                "Patchy rain nearby" -> R.drawable.cloud_rain
                "Patchy light rain with thunder" -> R.drawable.rain_lighting
                "Light rain" -> R.drawable.cloud_rain
                "Fog" -> R.drawable.cloud_sun
                "Moderate or heavy rain shower" -> R.drawable.cloud_rain
                "Light rain shower" -> R.drawable.cloud_rain
                else -> R.drawable.question
            }),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(size.dp)
        )
    }
}