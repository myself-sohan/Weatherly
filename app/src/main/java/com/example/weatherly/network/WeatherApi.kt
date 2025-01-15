package com.example.weatherly.network

import com.example.weatherly.model.freeweatherlatestmodel.Weather
import com.example.weatherly.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi {
    //Old Model
    //@GET(value = "data/2.5/forecast/daily")
    //New Model
    @GET(value = "data/2.5/forecast")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("units") units: String = "metric",
        @Query("appId") appId: String = API_KEY // your api key
    ): Weather

}