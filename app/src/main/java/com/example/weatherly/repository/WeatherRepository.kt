package com.example.weatherly.repository

import android.util.Log
import com.example.weatherly.data.DataOrException
import com.example.weatherly.data.WeatherDao
import com.example.weatherly.model.Favourite
import com.example.weatherly.model.Unit
import com.example.weatherly.model.freeweatherlatestmodel.City
import com.example.weatherly.model.freeweatherlatestmodel.Weather
import com.example.weatherly.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi,
    private val weatherDao: WeatherDao
)
{
    //Room -> Favourites
    fun getFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()
    suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite)
    suspend fun updateFavourite(favourite: Favourite) = weatherDao.updateFavourite(favourite)
    suspend fun deleteALlFavourites() = weatherDao.deleteAllFavourites()
    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite)
    suspend fun getFavById(city: String): Favourite = weatherDao.getFavById(city)
    
    //Room -> Units
    fun getUnits(): Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteALlUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)


    //Retrofit
    suspend fun getWeather(cityQuery: String, units: String):
            DataOrException<Weather , Boolean , Exception>
    {
        val response = try {
            api.getWeather(query = cityQuery, units = units)
        } catch (e: Exception) {
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)
        }
        return DataOrException(data = response)
    }
}