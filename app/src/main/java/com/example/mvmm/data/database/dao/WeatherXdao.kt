package com.example.mvmm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvmm.data.database.entity.WeatherXentity

@Dao
interface WeatherXdao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherX(weatherX: WeatherXentity)
/*
    @Query("SELECT * from weatherX")
    fun getWeatherX(): List<WeatherXentity>

 */

    @Query("SELECT * from weather")
    fun getWeatherX(): List<WeatherXentity>

    @Query("DELETE FROM weatherX")
    fun deleteAll()

    /*
    @Update
    fun updateWeatherx(weatherX: WeatherXentity)

     */
}