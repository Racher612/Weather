package com.project.weather.day.pres

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.weather.R
import com.project.weather.common.compose.CityIcon
import com.project.weather.common.compose.LoadingScreen
import com.project.weather.common.compose.PlusButton
import com.project.weather.common.compose.SwipeHintScreen
import com.project.weather.common.compose.TrashButton

@Composable
fun MainScreen(
    navigateToRoute : (String) -> Unit,
    viewModel: MainScreenViewModel = hiltViewModel(),
    modifier : Modifier
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
    ) { innerPadding ->
        Loader(
            viewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Loader(
    viewModel: MainScreenViewModel,
    modifier: Modifier
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            Box(
                modifier = Modifier
                    .requiredWidth(200.dp)
            ) {
            LocationSelector(
                viewModel = viewModel,
                modifier = Modifier
                ) }
            },
        drawerState = drawerState,
    ) {
        PopUpWindow(viewModel = viewModel)
        if ((viewModel.loading.value)) {
            LoadingScreen()
        } else {
            if (viewModel.currentCity.value == null){
                SwipeHintScreen()
            } else {
                WeatherPreview(
                    cityBar = viewModel.currentCity.value!!,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun LocationSelector(
    viewModel: MainScreenViewModel,
    modifier: Modifier
){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(colorResource(id = R.color.background_black))
            .fillMaxHeight()

    ) {
        val locations by viewModel.locations
         LazyColumn(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .requiredWidth(200.dp)
                    .padding(8.dp)
            ) {
                items(locations.toList()){ item ->
                    val city = item.city
                    CityIcon(
                        text = city,
                        pinOperation = {
                            if (item.pinned)
                                viewModel.deleteCityFromStorage(city)
                            else
                                viewModel.addCityToStorage(city)
                        },
                        deleteOperation = {
                            viewModel.deleteCity(city)
                        },
                        pinned = item.pinned,
                        onClick = {
                            viewModel.changeCurrentCity(city)
                        }
                    )
                }
                item{
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Box(modifier = Modifier.padding(vertical = 4.dp)){
                            TrashButton(onClick = viewModel::cleanCities)
                        }
                        Box(modifier = Modifier.padding(vertical = 4.dp)){
                            PlusButton(onClick = viewModel::openWindow)
                        }
                    }
                }
            }
        }
    }

@Composable
fun PopUpWindow(
    viewModel: MainScreenViewModel
){
    if (viewModel.popUp.value){
        Dialog(onDismissRequest = viewModel::openWindow) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = viewModel.searchCity.value,
                    onValueChange = viewModel::readCity
                )
                PlusButton {
                    viewModel.loadForecast(viewModel.searchCity.value)
                    viewModel.openWindow()
                }
            }
        }
    }
}