package com.project.weather.navigation.model

sealed class Routes(
    val route : String
){
    data object Settings : Routes("Settings")
    data object Main : Routes("Main")
}