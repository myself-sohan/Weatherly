package com.example.weatherly.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherly.model.Favourite
import com.example.weatherly.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao
{

    //Favourite Table
    @Query(value = " SELECT * from fav_tbl ")
    fun getFavourites(): Flow<List<Favourite>>

    @Query(value = " Select * from fav_tbl where city = :city ")
    suspend fun getFavById(city: String): Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query(value = " DELETE from fav_tbl ")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun  deleteFavourite(favourite: Favourite)

    //Unt Table
    @Query(value = " SELECT * from settings_tbl ")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query(value = " DELETE from settings_tbl ")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun  deleteUnit(unit: Unit)
}