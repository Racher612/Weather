package com.project.weather.day.data

import com.project.weather.day.domain.Binary

class HelperFactory{
    private var key : String = RetrofitData.key
    private var city : String = ""
    private var days : Int = RetrofitData.defaultDays
    private var aqi : Binary = RetrofitData.defaultAqi
    private var alerts : Binary = RetrofitData.defaultAlerts

    fun setKey(newKey : String) : HelperFactory {
        key = newKey
        return this
    }

    fun setCity(newCity : String) : HelperFactory {
        city = newCity
        return this
    }

    fun setDays(newDays : Int) : HelperFactory {
        days = newDays
        return this
    }

    fun setAQI(newAQI : Binary) : HelperFactory {
        aqi = newAQI
        return this
    }

    fun setAlerts(newAlerts : Binary) : HelperFactory {
        alerts = newAlerts
        return this
    }

    fun createHelper() : RetrofitHelper {
        return RetrofitHelper(key, city, days, aqi, alerts)
    }
}