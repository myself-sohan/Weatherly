package com.example.weatherly.model.freeweatherlatestmodel

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherObject>,
    val message: Int
)