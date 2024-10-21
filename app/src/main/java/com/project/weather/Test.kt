package com.project.weather

import com.project.weather.day.data.HelperFactory
import com.project.weather.day.domain.forecast.Forecast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response


fun main(){
    val cities = listOf(
        "Tokyo", "Delhi", "Shanghai", "Sao Paulo", "Mexico City", "Cairo", "Mumbai", "Beijing", "Dhaka", "Osaka",
        "New York", "Karachi", "Buenos Aires", "Chongqing", "Istanbul", "Kolkata", "Lagos", "Kinshasa", "Manila", "Rio de Janeiro",
        "Guangzhou", "Los Angeles", "Moscow", "Shenzhen", "Lahore", "Bangalore", "Paris", "Bogota", "Jakarta", "Chennai",
        "Lima", "Bangkok", "Seoul", "Nagoya", "Hyderabad", "London", "Tehran", "Chicago", "Chengdu", "Nanjing",
        "Wuhan", "Ho Chi Minh City", "Luanda", "Ahmedabad", "Kuala Lumpur", "Hong Kong", "Hanoi", "Riyadh", "Baghdad", "Santiago",
        "Surat", "Madrid", "Pune", "Suzhou", "Houston", "Dallas", "Toronto", "Dar es Salaam", "Miami", "Belo Horizonte",
        "Singapore", "Philadelphia", "Atlanta", "Fukuoka", "Khartoum", "Barcelona", "Johannesburg", "Saint Petersburg", "Qingdao", "Dalian",
        "Washington D.C.", "Yangon", "Alexandria", "Shijiazhuang", "Guadalajara", "Boston", "Melbourne", "Sydney", "Phoenix", "Monterrey",
        "Brasilia", "Busan", "Medellin", "Abidjan", "Harbin", "Kabul", "Denver", "Munich", "San Francisco", "Hamburg",
        "Montreal", "Tianjin", "Kuwait City", "Rome", "Stockholm", "Lusaka", "Casablanca", "Algiers", "Accra", "Port Harcourt",
    )
    val weathers = setOf<String>()

    val factory = HelperFactory()
        .setKey("5c15963fedb146f3b41202947243107")
        .setDays(1)
        .setCity("Moscow")
    var helper = factory.createHelper()
    var forecast : Response<Forecast>

    for (city in cities){
        factory.setCity(city)
        helper = factory.createHelper()
        runBlocking {
            forecast = helper.getForecast()
            forecast.body()?.current?.condition?.let {
                if (it.text !in weathers){
                    println(it.text)
                    weathers.plus(it.text)
                }
            }
        }
    }

    for (weather in weathers){
        println(weather)
    }

}