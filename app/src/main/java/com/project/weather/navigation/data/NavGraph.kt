package com.project.weather.navigation.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.project.weather.day.pres.MainScreen
import com.project.weather.navigation.model.Routes

@Composable
fun NavGraph(
    navController: NavHostController,

){
    val navigateToRoute: (String) -> Unit = {route ->
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Main.route
    ){
        composable(Routes.Main.route){
            MainScreen(navigateToRoute = navigateToRoute, modifier = Modifier)
        }
        composable(Routes.Settings.route){

        }
    }
}