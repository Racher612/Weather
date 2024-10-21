package com.project.weather.common.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.project.weather.R

@Composable
fun WeatherIcon(weather: String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = when(weather){
                "Light snow" -> R.drawable.cloud_snow
                "Clear" -> R.drawable.sunny
                "Sunny" -> R.drawable.sunny
                "Moderate rain" -> R.drawable.cloud_rain
                "Partly cloudy" -> R.drawable.cloud_sun
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
            tint = Color.Unspecified
        )
//        Text(text = weather)
    }

}