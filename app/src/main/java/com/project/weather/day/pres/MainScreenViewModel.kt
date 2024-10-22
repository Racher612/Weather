package com.project.weather.day.pres

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.weather.day.data.HelperFactory
import com.project.weather.day.data.LocationsRepository
import com.project.weather.day.data.RetrofitData
import com.project.weather.day.data.RetrofitHelper
import com.project.weather.day.domain.forecast.Forecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CityBar(
    var city: String,
    var pinned : Boolean,
    var forecast: Forecast
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val locationsRepository: LocationsRepository
) : ViewModel() {
    private var privateCurrentCity: MutableState<CityBar?> = mutableStateOf(null)
    var currentCity = privateCurrentCity

    //value defines if the dialog window active(true) or closed(false)
    private val privatePopUp = mutableStateOf(false)
    var popUp = privatePopUp

    //here stored the value user searches
    private val privateSearchCity = mutableStateOf("")
    var searchCity = privateSearchCity

    //list of cities loaded from local database
    private var cityList = mutableStateListOf<String>()

    //list of all cities displayed in the ModalNavigationDrawer
    private val privateLocations = mutableStateOf(setOf<CityBar>())
    var locations : MutableState<Set<CityBar>> = privateLocations

    //classes responsible for network actions
    private val factory : HelperFactory = HelperFactory()
    private var helper : RetrofitHelper = RetrofitHelper()

    //displays loading circle if false
    private val privateLoading = mutableStateOf(false)
    val loading: State<Boolean> = privateLoading

    init {
        viewModelScope.launch {
            val savedCities = locationsRepository.getSavedLocations().first()
            cityList.addAll(savedCities)

            cityList.forEach {city ->
                loadForecast(city, true)
            }
            if (privateLocations.value.isNotEmpty()){
                privateLoading.value = true
            }
        }
    }


    fun loadForecast(city : String, pinned: Boolean = false){
        privateLoading.value = true
        viewModelScope.launch {
            factory.setCity(city)
            helper = factory.createHelper()
            helper.getForecast().body()?.let {
                if (privateCurrentCity.value == null){
                    privateCurrentCity.value = CityBar(city, pinned, it)
                }
                privateLocations.value += CityBar(city, pinned, it)
                privateLoading.value = false
            }
        }
        searchCity.value = ""
    }

    fun addCityToStorage(city : String){
        viewModelScope.launch {
            locationsRepository.saveLocations(city)
            privateLocations.value = privateLocations.value.map { location ->
                if (location.city == city) {
                    location.copy(pinned = !location.pinned)
                } else {
                    location
                }
            }.toSet()
        }
    }

    fun deleteCityFromStorage(city : String){
        viewModelScope.launch {
            locationsRepository.deleteLocation(city)
            privateLocations.value = privateLocations.value.map { location ->
                if (location.city == city) {
                    location.copy(pinned = !location.pinned)
                } else {
                    location
                }
            }.toSet()
        }
    }

    fun deleteCity(city : String){
        viewModelScope.launch {
            privateLocations.value = privateLocations.value.filter { location ->
                location.city != city
            }.toSet()
            locationsRepository.deleteLocation(city)
        }
        privateCurrentCity.value = if (privateLocations.value.isNotEmpty())
            privateLocations.value.first() else
                null
    }

    fun changeCurrentCity(city : String){
        privateCurrentCity.value = privateLocations.value.find {
            it.city == city
        }!!
    }

    fun openWindow(){
        privatePopUp.value = !privatePopUp.value
    }

    fun readCity(city : String){
        privateSearchCity.value = city
    }

    fun cleanCities(){
        viewModelScope.launch {
            locationsRepository.cleanAllLocations()
        }
        privateLocations.value = emptySet()
        privateCurrentCity.value = null
    }
}