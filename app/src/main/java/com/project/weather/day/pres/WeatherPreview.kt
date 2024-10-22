package com.project.weather.day.pres

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.weather.R
import com.project.weather.common.compose.WeatherIcon
import com.project.weather.common.design.MediumText
import com.project.weather.day.domain.forecast.Forecast
import com.project.weather.day.domain.forecast.Hour


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
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            cityBar.forecast.let{
                item{
                    Temperature(it)
                }
                item{
                    Conditions(it)
                }
                item{
                    HourTable(it)
                }
            }
        }
    }
}

@Composable
fun HourTable(forecast: Forecast) {
    LazyRow{
        items(24) {
                Hour(
                    hour = forecast.forecast.forecastday.first().hour[it],
                    modifier = Modifier.width(IntrinsicSize.Max)
                )
            }
        }
    }


@Composable
fun Hour(
    hour: Hour,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier
        .padding(8.dp)
        .border(2.dp, colorResource(id = R.color.white), RoundedCornerShape(4.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)){
            MediumText(text = hour.time.split(" ")[1])
            MediumText(text = stringResource(id = R.string.temperature, hour.temp_c))
            WeatherIcon(weather = hour.condition.text)
            MediumText(text = "rain: ${hour.chance_of_rain}%")
        }
    }
}

@Composable
fun Conditions(
    it : Forecast
){
    Row(
        horizontalArrangement = Arrangement.Center,

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            MediumText(text = stringResource(id = R.string.wetness))
            MediumText(text = stringResource(id = R.string.precipitation))
            MediumText(text = stringResource(id = R.string.wind))
            MediumText(text = stringResource(id = R.string.pressure))
        }
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            //wetness
            MediumText(text = "${it.current.humidity}${stringResource(id = R.string.percent)}")
            //precipitation
            MediumText(text = stringResource(id = R.string.millimeter, it.current.precip_mm))
            //Wind
            MediumText(text = stringResource(id = R.string.km_hour, it.current.wind_kph))
            //Pressure
            MediumText(text = stringResource(id = R.string.millimeter, it.current.precip_mm))
        }
    }
}

@Composable
fun Temperature(it : Forecast){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        //city & country
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = null,
                tint = colorResource(id = R.color.white),
                modifier = Modifier.size(25.dp)
            )
            MediumText(
                text = "${it.location.region}, ${it.location.country}",
            )
        }
        //weather description
        WeatherIcon(
            weather = it.current.condition.text,
            size = 100
        )
        MediumText(
            text = it.current.condition.text,
            color = colorResource(id = R.color.yellow)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            MediumText(
                text = stringResource(id = R.string.temperature, it.current.temp_c)
            )
            MediumText(
                text = stringResource(id = R.string.feels_like, it.current.feelslike_c)
            )
        }
    }
}