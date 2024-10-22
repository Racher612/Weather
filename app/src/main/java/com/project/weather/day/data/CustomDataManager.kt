package com.project.weather.day.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationsRepository(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "PinnedCities")

    private val LOCATIONS_KEY = stringSetPreferencesKey("locations")

    suspend fun saveLocations(location: String) {
        context.dataStore.edit { preferences ->
            val currentLocations = preferences[LOCATIONS_KEY] ?: emptySet()
            preferences[LOCATIONS_KEY] = currentLocations + location
        }
    }

    suspend fun deleteLocation(location: String) {
        context.dataStore.edit { preferences ->
            val currentLocations = preferences[LOCATIONS_KEY] ?: emptySet()
            preferences[LOCATIONS_KEY] = currentLocations - location
        }
    }

    suspend fun cleanAllLocations(){
        context.dataStore.edit { preferences ->
            preferences[LOCATIONS_KEY] = emptySet()
        }
    }

    fun getSavedLocations(): Flow<Set<String>> {
        return context.dataStore.data
            .map { preferences ->
                preferences[LOCATIONS_KEY] ?: emptySet()
            }
        }
}