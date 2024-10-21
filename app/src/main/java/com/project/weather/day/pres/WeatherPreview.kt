package com.project.weather.day.pres

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import com.project.weather.R
import com.project.weather.common.compose.WeatherIcon
import com.project.weather.common.design.MediumText


@Composable
fun WeatherPreview(
    cityBar: CityBar,
    modifier: Modifier
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colorResource(id = R.color.purple),
                        colorResource(id = R.color.black)
                    ),
                    startX = 10.0f,
                    endX = 2000.0f
                )
            )
    ){
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            cityBar.forecast.let{
                //weather description
                item{
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MediumText(
                            text = it.current.temp_c.toString() + " °C"
                        )

                        WeatherIcon(weather = it.current.condition.text)
                        MediumText(
                            text = it.current.condition.text,
                        )
                    }
                }
                //city & country
                item{
                    MediumText(
                        text = "${it.location.region}, ${it.location.country}",
                    )
                }
                //wetness
                //wind
                //sunrise
            }
        }
    }
}
