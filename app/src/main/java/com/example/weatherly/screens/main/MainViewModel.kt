package com.example.weatherly.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.data.DataOrException
import com.example.weatherly.model.freeweatherlatestmodel.Weather
import com.example.weatherly.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel()
{
//        suspend fun getWeatherData(city : String):
//        DataOrException<Weather, Boolean, Exception>
//        {
//            return repository.getWeather(cityQuery = city)
//        }
        private val _weatherData = mutableStateOf(DataOrException<Weather, Boolean, Exception>(loading = true))
            val weatherData: MutableState<DataOrException<Weather, Boolean, Exception>> = _weatherData

            fun getWeatherData(city: String, units: String) {
                viewModelScope.launch {
                    try {
                        _weatherData.value = DataOrException(loading = true)
                        val result = repository.getWeather(cityQuery = city, units = units)
                        _weatherData.value = result
                    } catch (e: Exception) {
                        _weatherData.value = DataOrException(e = e)
                    }
                }
            }

}