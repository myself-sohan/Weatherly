package com.example.weatherly.di

import android.content.Context
import androidx.room.Room
import com.example.weatherly.data.WeatherDao
import com.example.weatherly.data.WeatherDatabase
import com.example.weatherly.network.WeatherApi
import com.example.weatherly.repository.WeatherRepository
import com.example.weatherly.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao
    = weatherDatabase.weatherDao()


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase
    = Room.databaseBuilder(
        context,
        WeatherDatabase::class.java,
        "weather_database"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideWeatherRepository(api: WeatherApi, weatherDao: WeatherDao) = WeatherRepository(api, weatherDao)


    @Singleton
    @Provides
    fun provideOpenWeatherApi() : WeatherApi
    {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}